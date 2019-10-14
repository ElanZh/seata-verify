package elan.verify.seata.user.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * @author 张一然
 * @date 2019/10/12 16:21
 * @Description
 */
@Configuration
public class DataSourceConfig {
    /**
     * 数据源属性配置
     * {@link DataSourceProperties}
     */
    private DataSourceProperties dataSourceProperties;

    public DataSourceConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    /**
     * 配置数据源代理，用于事务回滚
     *
     * @return The default datasource
     * @see DataSourceProxy
     */
    @Primary
    @Bean("dataSource")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
        hikariConfig.setUsername(dataSourceProperties.getUsername());
        hikariConfig.setPassword(dataSourceProperties.getPassword());
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        return new DataSourceProxy(dataSource);
    }
}

