package com.adjh.springboot3tierform.config;

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
 * 해당 클래스에서는 Mybatis 와 Hikari 연동하는 환경설정이 포함되었습니다.
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfig {
    final ApplicationContext applicationContext;


    public DBConfig(ApplicationContext ac) {
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

    /**
     * SqlSessionFactory 구성
     * - MyBatis 설정 파일을 읽어들여 SqlSession을 생성하는 팩토리 클래스
     * 1. DataSource 지정
     * 2. Mapper.xml 파일 연결
     * 3. 최종 구성 객체 반환
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);                                                   // 데이터베이스와 연결을 위한 DataSource 지정
        sessionFactory.setTypeAliasesPackage("com.adjh.springboot3tierform.model.dto");
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/common-mybatis-config.xml"));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("mapper/*.xml"));      // 구성한 Mapper 파일의 경로를 지정

        return sessionFactory.getObject();                                                          // SqlSessionFactory 객체 구성
    }

    /**
     * SqlSessionTemplate 구성
     * - SQL 실행과 트랜잭션 관리를 담당하며, SqlSession 인스턴스의 생명주기를 관리하고 필요한 설정을 자동으로 수행
     *
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}

