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

import static com.songsir.config.StuDataSourceConfig.getDataSource;

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

    /**
     * 扫描dao包
     */
    static final String TEA_PACKAGE = "com.songsir.dao.mapper2";

    @Value("${spring.datasource.two.url}")
    private String salveUrl;

    @Value("${spring.datasource.two.username}")
    private String salveUsername;

    @Value("${spring.datasource.two.password}")
    private String salvePassword;

    @Bean(name = "salveDataSource")
    public DataSource getSalveDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.salveUrl);
        druidDataSource.setUsername(this.salveUsername);
        druidDataSource.setPassword(this.salvePassword);
        return getDataSource(druidDataSource);
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
