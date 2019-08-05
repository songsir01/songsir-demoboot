
[toc]
# 一、首先搭建SpringBoot项目

 - 新建SpringBoot项目，参考博客：[https://blog.csdn.net/SongSir001/article/details/80436415](https://blog.csdn.net/SongSir001/article/details/80436415)
 - 这是以前使用eclipse搭建的一个demo，或者从[https://github.com/songsir01/songsir-demoboot.git](https://github.com/songsir01/songsir-demoboot.git)下载最近使用idea搭建的完善后的demo
 - 项目格式如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181207150901311.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)

# 二、配置数据源

## 1、配置第一个数据源

 ### （1）引入druid连接池依赖

```
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.9</version>
    </dependency>
```

### （2）在properties文件中添加数据库和连接池配置


```
# 数据源 1
spring.datasource.one.url=jdbc:mysql://localhost:3306/songsirsdb?useUnicode=true&characterEncoding=UTF-8
spring.datasource.one.username=root
spring.datasource.one.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

# druid连接池配置
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.initialSize=3
spring.datasource.maxActive=20
spring.datasource.removeAbandoned=true
spring.datasource.removeAbandonedTimeout=1800
spring.datasource.timeBetweenEvictionRunsMillis=60000
spring.datasource.validationQuery=SELECT 'x'
spring.datasource.testWhileIdle=true
spring.datasource.testOnBorrow=true
spring.datasource.testOnReturn=true
spring.datasource.filters=stat,wall,log4j
spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

### （3）新建配置类读取配置文件


```
package com.songsir.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @PackageName com.songsir.config
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 11:27 2018/12/7
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
@Configuration
@MapperScan(basePackages = StuDataSourceConfig.STUPACKAGE, sqlSessionFactoryRef = "defaultSqlSessionFactory")
public class StuDataSourceConfig {

    // 扫描dao包
    static final String STUPACKAGE = "com.songsir.dao.mapper";

    @Value("${spring.datasource.one.url}")
    private String url;

    @Value("${spring.datasource.one.username}")
    private String username;

    @Value("${spring.datasource.one.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.removeAbandoned}")
    private boolean removeAbandoned;

    @Value("${spring.datasource.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.connectionProperties}")
    private Properties connectionProperties;

    @Bean(name = "dataSource")
    @Primary
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        // druid配置
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setRemoveAbandoned(removeAbandoned);
        druidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        try {
            druidDataSource.setFilters(filters);
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: " + e);
        }
        druidDataSource.setConnectProperties(connectionProperties);
        return druidDataSource;
    }

    @Bean(name = "defaultSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sessionFactoryBean.setMapperLocations(resolver.getResources("com/songsir/dao/mapper/*.xml"));
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "defaultSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate defaultSqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "defaultTransactionManager")
    @Primary
    public DataSourceTransactionManager defaultAnnotationDrivenTransactionManager(){
        return new DataSourceTransactionManager(getDataSource());
    }
}

```
## 2、第二个数据源

### （1）不同的mapper使用不同的数据源

在dao下新建包mapper2，保证不同的数据源使用不同的mapper
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181207152242135.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1NvbmdTaXIwMDE=,size_16,color_FFFFFF,t_70)

### （2）在Properties里面添加第二个数据源

```
# 数据源 2 和数据源 1 地址一样数据库不一样
spring.datasource.two.url=jdbc:mysql://localhost:3306/songsirsdbtwo?useUnicode=true&characterEncoding=UTF-8
spring.datasource.two.username=root
spring.datasource.two.password=root

```
### （3）新建配置文件读取第二个数据源

```
package com.songsir.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @PackageName com.songsir.config
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 11:28 2018/12/7
 * @Description:
 * @Copyright Copyright (c) 2018, songyapeng@shopin.cn All Rights Reserved.
 */
@Configuration
@MapperScan(basePackages = TeaDataSourceConfig.TEAPACKAGE, sqlSessionFactoryRef = "salveSqlSessionFactory")
public class TeaDataSourceConfig {

    // 扫描dao包
    static final String TEAPACKAGE = "com.songsir.dao.mapper2";

    @Value("${spring.datasource.two.url}")
    private String salveUrl;

    @Value("${spring.datasource.two.username}")
    private String salveUsername;

    @Value("${spring.datasource.two.password}")
    private String salvePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.removeAbandoned}")
    private boolean removeAbandoned;

    @Value("${spring.datasource.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.connectionProperties}")
    private Properties connectionProperties;

    @Bean(name = "salveDataSource")
    public DataSource getSalveDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.salveUrl);
        druidDataSource.setUsername(salveUsername);
        druidDataSource.setPassword(salvePassword);
        druidDataSource.setDriverClassName(driverClassName);
        // druid配置
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setRemoveAbandoned(removeAbandoned);
        druidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        try {
            druidDataSource.setFilters(filters);
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: " + e);
        }
        druidDataSource.setConnectProperties(connectionProperties);
        return druidDataSource;
    }

    @Bean(name = "salveSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("salveDataSource") DataSource dataSource) {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sessionFactoryBean.setMapperLocations(resolver.getResources("com/songsir/dao/mapper2/*.xml"));
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "salveSqlSessionTemplate")
    public SqlSessionTemplate oggSqlSessionTemplate(SqlSessionFactory salveSqlSessionFactory) {
        return new SqlSessionTemplate(salveSqlSessionFactory);
    }

    @Bean(name = "salveTransactionManager")
    public DataSourceTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(getSalveDataSource());
    }
}

```
注意两个配置类的不同之处：
 - 数据库地址、用户、密码等字段名保证不一样
 - 扫描的mapper相对应


## 3、写接口测试

两个数据库分别有student和teacher表如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181207153206885.png)
下面两个接口分别查询student表和teacher表：

```
    @RequestMapping("/studentDemo")
    public String firstDemo(int sid) {
        Student student = songsirService.getDemoStudent(sid);
        System.out.println(student.toString());
        return student.toString();
    }

    @RequestMapping("/teacherDemo")
    public String teacherDemo(int sid) {
        Teacher teacher = songsirService.getDemoTeacher(sid);
        System.out.println(teacher.toString());
        return teacher.toString();
    }
```
打印结果：

```
Student{SID='1', Sname='小明', Sage='1995-01-01 00:00:00.0', Ssex='男'}
Teacher{sid=1, name='李四', age=33}
```

# 三、完整项目

了解具体实现请参考：[https://github.com/songsir01/songsir-demoboot.git](https://github.com/songsir01/songsir-demoboot.git)