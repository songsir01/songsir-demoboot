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
@MapperScan(basePackages = StuDataSourceConfig.STU_PACKAGE, sqlSessionFactoryRef = "defaultSqlSessionFactory")
public class StuDataSourceConfig {

    // 扫描dao包
    static final String STU_PACKAGE = "com.songsir.dao.mapper";

    @Value("${spring.datasource.one.url}")
    private String url;

    @Value("${spring.datasource.one.username}")
    private String username;

    @Value("${spring.datasource.one.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    protected static String driverClassName;

    @Value("${spring.datasource.initialSize}")
    protected static int initialSize;

    @Value("${spring.datasource.maxActive}")
    protected static int maxActive;

    @Value("${spring.datasource.removeAbandoned}")
    protected static boolean removeAbandoned;

    @Value("${spring.datasource.removeAbandonedTimeout}")
    protected static int removeAbandonedTimeout;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    protected static int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.validationQuery}")
    protected static String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    protected static boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    protected static boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    protected static boolean testOnReturn;

    @Value("${spring.datasource.filters}")
    protected static String filters;

    @Value("${spring.datasource.connectionProperties}")
    protected static Properties connectionProperties;

    @Bean(name = "dataSource")
    @Primary
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.url);
        druidDataSource.setUsername(this.username);
        druidDataSource.setPassword(this.password);
        return getDataSource(druidDataSource);
    }

    protected static DataSource getDataSource(DruidDataSource druidDataSource) {
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
    public SqlSessionTemplate defaultSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "defaultTransactionManager")
    @Primary
    public DataSourceTransactionManager defaultAnnotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }
}
