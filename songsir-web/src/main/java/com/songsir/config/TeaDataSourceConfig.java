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
 * @Auther: SongYapeng
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
