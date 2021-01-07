package com.itheima.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


/**
 * 和数据库相关的配置类
 * 1. SpringConfiguration
 *          @PropertySource("jdbcConfig.properties")
 *          @Import({JdbcConfig.class})
 * 2. 配置数据库信息
 *          jdbcConfig.properties
 * 3. 创建jdbcTemplate对象
 * 4. 创建DataSource数据源对象
 */
public class JdbcTemplateConfig {
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean("jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean("dataSource")
    public DataSource createDatasource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
