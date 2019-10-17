# seata-verify

阿里开源分布式事务解决方案Seata验证项目
2019-10-10

注册中心和配置中心使用nacos
seata-server同样使用nacos注册和配置

> 按照官方文档的说法，模拟这个案例：
> <br/> 1.用户下单，减扣库存
> <br/> 2.减扣库存完成，减扣用户余额
> <br/> 3.减扣用户余额完成，创建订单


- 注册中心```Nacos```
- 配置中心```Nacos```
- 数据库```MySQL```

1. 启动Nacos
  
    下载安装nacos etc.
    
    在nacos中导入 ```seataVerify-config```下的zip包，这个是各个微服务的配置，
    包括hysrix，ribbon，数据库，jpa
    
2. 启动seata-server

    - 修改```nacos-config.txt```
        ```
            ...
      
            # 告诉协调器有几个要注册的事务，服务名+-fescar-service-group，服务名=spring.application.name，所以这个值一定要配置
            service.vgroup_mapping.seata-verify-biz-fescar-service-group=default
            service.vgroup_mapping.seata-verify-storage-fescar-service-group=default
            service.vgroup_mapping.seata-verify-order-fescar-service-group=default
            service.vgroup_mapping.seata-verify-user-fescar-service-group=default
            
            ...
           
            # 事务日志记录在数据库里
            store.mode=db
            store.db.datasource=druid
            store.db.db-type=mysql
            store.db.url=jdbc:mysql://127.0.0.1:3306/seata?useUnicode=true
            store.db.user=root
            store.db.password=admin
            ...
        ```

        执行 ```bash nacos-config.sh 192.168.1.30``` 将```nacos-config.txt```发送到nacos
    
    - 在seata-server的日志库中执行```db_store.sql```脚本，
    
        **注意：脚本中的两个字段长度只有32，如果应用名过长，请修改字段长度 ```application_id```,```transaction_service_group```**

    - 修改```registry.conf```，指定注册到nacos，配置中心nacos
        ```
        registry {
            type = "nacos"
            nacos {
                serverAddr = "192.168.1.30"
                namespace = "public"
                cluster = "default"
            }
        }
        config {
            type = "nacos"
            nacos {
                serverAddr = "192.168.1.30"
                namespace = "public"
                cluster = "default"
            }
        }
        ```
      
    最后，执行 ```bash seata-server.sh -h 192.168.1.22 -m db```
    
    **注意：-h 后边的IP不可以使用127.0.0.1和0.0.0.0，否则注册中心里会出现虚拟IP，这里需要明确指定本机IP**
    
3. 修改各个微服务
    - resource中加入```registry.conf```文件并指定注册中心和配置中心
    - pom中加入两个依赖：
        ```xml
        <!--seata-->
        <dependency>
            <groupId>io.seata</groupId>
            <artifactId>seata-all</artifactId>
            <version>${seata.version}</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-seata</artifactId>
            <version>2.1.0.RELEASE</version>
            <exclusions>
                <exclusion>
                    <artifactId>seata-all</artifactId>
                    <groupId>io.seata</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        ```
    - 每个微服务的数据库中，都需要执行seata-server包中的 ```db_undo_log.sql```以建立回滚重做表
    - biz服务中加入注解开启全局事务```@GlobalTransactional```
    
        **注意：注解下的方法必须是public，否则不会被代理，事务不生效**
        
4. 启动四个微服务 biz,storage,order,user 发送GET请求到 ```http://localhost:8883/orderBiz/createOrder?userId=1```

