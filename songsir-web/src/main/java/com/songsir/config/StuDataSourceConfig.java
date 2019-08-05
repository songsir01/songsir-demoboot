package com.songsir.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(TeaDataSourceConfig.class);

    /** 扫描dao包 */
    static final String STU_PACKAGE = "com.songsir.dao.mapper";

    @Value("${spring.datasource.one.url}")
    private String url;

    @Value("${spring.datasource.one.username}")
    private String username;

    @Value("${spring.datasource.one.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    protected String driverClassName;

    @Value("${spring.datasource.initialSize}")
    protected int initialSize;

    @Value("${spring.datasource.maxActive}")
    protected int maxActive;

    @Value("${spring.datasource.removeAbandoned}")
    protected boolean removeAbandoned;

    @Value("${spring.datasource.removeAbandonedTimeout}")
    protected int removeAbandonedTimeout;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    protected int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.validationQuery}")
    protected String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    protected boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    protected boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    protected boolean testOnReturn;

    @Value("${spring.datasource.filters}")
    protected String filters;

    @Value("${spring.datasource.connectionProperties}")
    protected Properties connectionProperties;

    @Bean(name = "dataSource")
    @Primary
    public DataSource getDataSource() {
        DruidDataSource stuDruidDataSource = new DruidDataSource();
        stuDruidDataSource.setUrl(this.url);
        stuDruidDataSource.setUsername(this.username);
        stuDruidDataSource.setPassword(this.password);
        stuDruidDataSource.setDriverClassName(driverClassName);
        stuDruidDataSource.setInitialSize(initialSize);
        stuDruidDataSource.setMaxActive(maxActive);
        stuDruidDataSource.setRemoveAbandoned(removeAbandoned);
        stuDruidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        stuDruidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        stuDruidDataSource.setValidationQuery(validationQuery);
        stuDruidDataSource.setTestWhileIdle(testWhileIdle);
        stuDruidDataSource.setTestOnBorrow(testOnBorrow);
        stuDruidDataSource.setTestOnReturn(testOnReturn);
        try {
            stuDruidDataSource.setFilters(filters);
        } catch (SQLException e) {
            log.error("主数据源出错" + e);
        }
        stuDruidDataSource.setConnectProperties(connectionProperties);
        return stuDruidDataSource;
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
