# 写一个纯注解的，有事务控制的业务
1. 创建数据库表和实体类"Account"
2. 创建SpringConfiguration类，实现注解配置
    - pom.xml 配置 spring-context
3. 创建JdbcTemplateCofig类，实现数据库操作
    - pom.xml 配置 spring-jdbc，mysql
    - 配置jdbc.properties文件，写入mysql基本信息
    - 提供jdbcTemplate和dataSource
4. 创建Service层接口和实现类，并使用注解，让spring管理
    - 提供accountService
5. 创建Dao层接口和实现类，并使用注解，让spring管理
    - 提供accountDao
6. 创建测试类MySpringTest，测试
    - pom.xml配置 Junit和spring-test
7. 写TransactionConfig类
8. 在SpringConfiguration类中开启事务支持，记得导入Transaction.class
9. 在AccountService类中配置事务