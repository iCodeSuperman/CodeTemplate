package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 1. 注明这是spring配置类
@Configuration
// 2. 注明要扫描的包
@ComponentScan("com.itheima")
// 3. 注入jdbc要使用的properties文件
@PropertySource("jdbcConfig.properties")
// 4. 导入其他配置类
@Import({JdbcTemplateConfig.class, TransactionConfig.class})
// 5. 开启事务支持
@EnableTransactionManagement
public class SpringConfiguration {

}
