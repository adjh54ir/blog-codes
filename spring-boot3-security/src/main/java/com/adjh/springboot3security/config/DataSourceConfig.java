package com.adjh.springboot3security.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 데이터베이스와 연결 및 SQL Mapper(MyBatis)와의 연결을 담당하는 설정 클래스입니다.
 *
 * @author : jonghoon
 * @fileName : DataSourceConfig
 * @since : 10/1/24
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class DataSourceConfig {
    final ApplicationContext applicationContext;

    public DataSourceConfig(ApplicationContext ac) {
        this.applicationContext = ac;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        session.setDataSource(dataSource);
        session.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
        session.setTypeAliasesPackage("com.adjh.springboot3security.model.dto");
        session.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/common-mybatis-config.xml"));
        return session.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
