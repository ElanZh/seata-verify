# seata-verify

阿里开源分布式事务解决方案Seata验证项目
2019-10-10

注册中心和配置中心使用nacos
seata-server同样使用nacos注册和配置

> 按照官方文档的说法，模拟这个案例：
> <br/> 1.用户下单，减扣库存
> <br/> 2.减扣库存完成，减扣用户余额
> <br/> 3.减扣用户余额完成，创建订单
