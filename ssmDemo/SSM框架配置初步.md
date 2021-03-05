[toc]

# 思路
**SSM整合可以使用多种方式，这里使用XML+注解的方式**  
**步骤**  
[1] 搭建整合环境  
[2] 先把spring的配置搭建完成  
[3] 在使用spring整合springmvc框架  
[4] 最后使用spring整合mybatis框架  

# 一. 搭建整合环境
1. 创建数据库和表结构
```mysql
create database ssm;
use ssm;
create table account(
	id int PRIMARY key auto_increment,
	name varchar(20),
	money DOUBLE
);

insert into account(name, money) VALUES("吴彦祖", 200.0);
insert into account(name, money) VALUES("彭于晏", 100.0);
insert into account(name, money) VALUES("刘德华", 1200.0);
```
2. 创建maven工程
- 选择 org.apache.maven.archetypes:maven-archetype-webapp
- 配置pom.xml
> 1. spring：aspectjweaver,spring-aop,spring-context
> 2. springmvc：spring-web，spring-webmvc
> 3. 单元测试：spring-test，junit
> 4. spring事物管理：spring-tx
> 5. 数据库相关：spring-jdbc，mysql-connector-java，c3p0
> 6. Servlet&JSP：servlet-api，jsp-api，jstl(jsp页面写el表达式)
> 7. 日志：log4j，slf4j-api，slf4j-log4j12
> 8. mybatis：mybatis，mybatis-spring(spring整合mybatis框架)

3. 编写实体类 Account
    - Integer id
    - String name
    - Double money
4. 编写dao接口 AccountDao
    - List<Account> findAll()
    - void saveAccount()
5. 编写service接口和实现类 AccountService
    - List<Account> findAll()
    - void saveAccount()


# 二. Spring框架代码编写
## 搭建和测试spring开发环境
1. 创建applicationContext.xml的配置文件  
**开启注解扫描，要扫描的是dao层和service层的注解，忽略web层的注解，web层交给springmvc去管理**

2. 测试框架是否搭建成功


# 三. Spring整合SpringMVC框架
## 1. 搭建和测试SpringMVC的开发环境
1. web.xml配置DispatcherServlet前端控制器
2. web.xml配置DispatcherServlet过滤器解决中文乱码

```xml
<!DOCTYPE web-app PUBLIC
        "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
        "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
    <display-name>Archetype Created Web Application</display-name>

    <!-- 1. 配置前端控制器：服务器启动必须加载，需要加载springmvc.xml配置文件 -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 配置初始化参数，创建完DispatcherServlet对象，加载springmvc.xml配置文件 -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <!-- 特别注意这里是classpath: -->
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <!-- 服务器启动的时候，让DispatcherServlet对象创建 -->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!-- 2. 配置解决中文乱码的过滤器 -->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

</web-app>

```

3. 创建springmvc.xml配置文件，编写配置文件
    - **开启注解扫描，只扫描web层**
    - **配置视图解析器** 
    - **设置静态资源不过滤**
    - **开启springmvc框架的注解支持**

4. 测试框架是否搭建成功


## 2. Spring整合SpringMVC的框架
1. 目的：在Controller中能成功调用service对象中的方法
2. 在项目启动时，使得能够加载applicationContext.xml的配置文件，在web.xml中配置ContextLoaderListener监听器（该监听器只能加载WEB-INF目录下的文件，所以需要额外配置传入参数）

```xml
<!-- 3. 配置Spring的监听器，使得项目启动就能加载applicationContext.xml文件 -->
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listenerclass>
</listener> <!-- 配置加载类路径的配置文件 -->
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
</context-param>

```

3. 在Controller中注入service对象，调用service对象的方法进行测试
AccountController.java中，注入AccountService对象


# 四. Spring整合Mybatis框架
## 1.搭建和测试Mybatis的环境
1. 创建SqlMapConfig.xml配置文件，编写核心配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="jdbcConfig.properties"></properties>

    <settings>
        <!--true 开启延迟加载-->
        <setting name="lazyLoadingEnabled" value="true"/>
        <!--false 开启按需加载-->
        <setting name="aggressiveLazyLoading" value="false"/>
    </settings>

    <!--别名-->
    <typeAliases>
        <typeAlias alias="Accout" type="com.itheima.domain.Account"></typeAlias>
    </typeAliases>


    <environments default="mysql">
        <environment id="mysql">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
    <!-- 
        配置dao接口的位置，有两种方式
        1. 使用mapper标签配置class属性
        2. 使用package标签直接指定dao接口所在的包   
    -->
        <package name="com.itheima.dao"/>
    </mappers>
</configuration>
```

2. 在AccountDao接口的方法上添加注解，编写SQL语句
3. 测试框架是否搭建成功


## 2. Spring整合Mybatis框架
1. 目的：把SqlMapConfig.xml配置文件中的内容配置到applicationContext配置文件中

```xml
<!-- 1. 把SqlMapConfig.xml 中的内容配置到applicationContext.xml中 -->
<!-- 配置C3P0的连接池对象 -->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver" />
    <property name="url" value="jdbc:mysql:///ssm" />
    <property name="username" value="root" />
    <property name="password" value="root" />
</bean>

<!-- 配置SqlSession的工厂 -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
</bean>

<!-- 配置扫描dao的包 -->
<bean id="mapperScanner" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.itheima.dao"/>
</bean>
```

2. 在AccountDao接口中添加@Repository注解
3. 在service中注入dao对象，进行测试

4. 配置sring声明式事物管理

```xml
<!--2. 配置Spring框架声明式事务管理-->
<!--配置事务管理器-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource" />
</bean>

<!--配置事务通知-->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
        <tx:method name="find*" read-only="true"/>
        <tx:method name="*" isolation="DEFAULT"/>
    </tx:attributes>
</tx:advice>

<!--配置AOP增强-->
<aop:config>
    <aop:advisor advice-ref="txAdvice" pointcut="execution(* com.itheima.service.impl.*ServiceImpl.*(..))"/>
</aop:config>
```


