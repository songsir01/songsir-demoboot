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
@MapperScan(basePackages = TeaDataSourceConfig.TEA_PACKAGE, sqlSessionFactoryRef = "salveSqlSessionFactory")
public class TeaDataSourceConfig {

    private Logger log = LoggerFactory.getLogger(TeaDataSourceConfig.class);

    /** 扫描dao包 */
    static final String TEA_PACKAGE = "com.songsir.dao.mapper2";

    @Value("${spring.datasource.two.url}")
    private String salveUrl;

    @Value("${spring.datasource.two.username}")
    private String salveUsername;

    @Value("${spring.datasource.two.password}")
    private String salvePassword;

    @Value("${spring.datasource.driver-class-name}")
    protected String driverClassName;

    @Value("${spring.datasource.salve.initialSize}")
    protected int salveInitialSize;

    @Value("${spring.datasource.salve.maxActive}")
    protected int salveMaxActive;

    @Value("${spring.datasource.salve.removeAbandoned}")
    protected boolean salveRemoveAbandoned;

    @Value("${spring.datasource.salve.removeAbandonedTimeout}")
    protected int salveRemoveAbandonedTimeout;

    @Value("${spring.datasource.salve.timeBetweenEvictionRunsMillis}")
    protected int salveTimeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.salve.validationQuery}")
    protected String salveValidationQuery;

    @Value("${spring.datasource.salve.testWhileIdle}")
    protected boolean salveTestWhileIdle;

    @Value("${spring.datasource.salve.testOnBorrow}")
    protected boolean salveTestOnBorrow;

    @Value("${spring.datasource.salve.testOnReturn}")
    protected boolean salveTestOnReturn;

    @Value("${spring.datasource.salve.filters}")
    protected String salveFilters;

    @Value("${spring.datasource.salve.connectionProperties}")
    protected Properties salveConnectionProperties;


    @Bean(name = "salveDataSource")
    public DataSource getSalveDataSource() {
        DruidDataSource teaDruidDataSource = new DruidDataSource();
        teaDruidDataSource.setUrl(this.salveUrl);
        teaDruidDataSource.setUsername(this.salveUsername);
        teaDruidDataSource.setPassword(this.salvePassword);
        teaDruidDataSource.setDriverClassName(this.driverClassName);
        teaDruidDataSource.setInitialSize(this.salveInitialSize);
        teaDruidDataSource.setMaxActive(this.salveMaxActive);
        teaDruidDataSource.setRemoveAbandoned(this.salveRemoveAbandoned);
        teaDruidDataSource.setRemoveAbandonedTimeout(this.salveRemoveAbandonedTimeout);
        teaDruidDataSource.setTimeBetweenEvictionRunsMillis(this.salveTimeBetweenEvictionRunsMillis);
        teaDruidDataSource.setValidationQuery(this.salveValidationQuery);
        teaDruidDataSource.setTestWhileIdle(this.salveTestWhileIdle);
        teaDruidDataSource.setTestOnBorrow(this.salveTestOnBorrow);
        teaDruidDataSource.setTestOnReturn(this.salveTestOnReturn);
        try {
            teaDruidDataSource.setFilters(salveFilters);
        } catch (SQLException e) {
            log.error("从数据源出错" + e);
        }
        teaDruidDataSource.setConnectProperties(salveConnectionProperties);
        return teaDruidDataSource;
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
