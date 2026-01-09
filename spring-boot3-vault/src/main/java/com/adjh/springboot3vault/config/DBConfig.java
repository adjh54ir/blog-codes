package com.adjh.springboot3vault.config;

import com.adjh.springboot3vault.properties.VaultDbProperties;
import com.adjh.springboot3vault.properties.VaultKVProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


/**
 * 최초 데이터 베이스 연결 설정을 구성하는 설정 클래스입니다.
 */
@Configuration
public class DBConfig {

    private final ApplicationContext applicationContext;
    private final VaultKVProperties kvProperties;
    private final VaultDbProperties dbProperties;

    public DBConfig(ApplicationContext applicationContext, VaultKVProperties kvProperties, VaultDbProperties dbProperties) {
        this.applicationContext = applicationContext;
        this.kvProperties = kvProperties;
        this.dbProperties = dbProperties;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        System.out.println("kvProperties = " + kvProperties.getDbUrl());
        System.out.println("kvProperties = " + kvProperties.getDbPort());
        System.out.println("kvProperties = " + kvProperties.getDbName());
        // KV Secret Engine 내에 조회한 속성을 조회하여 세팅함.
        hikariConfig.setJdbcUrl(
                String.format("jdbc:postgresql://%s:%s/%s",
                        kvProperties.getDbUrl(),
                        kvProperties.getDbPort(),
                        kvProperties.getDbName())
        );
        // DB Secret Engine 내에서 조회한 속성을 조회하여 세팅함.(동적 사용자, 비밀번호)
        hikariConfig.setUsername(dbProperties.getUsername());
        hikariConfig.setPassword(dbProperties.getPassword());

        String username = hikariConfig.getUsername();
        String password = hikariConfig.getPassword();

        System.out.println("동적 사용자 >> " + username);
        System.out.println("동적 패스워드 >> " + password);
        // 기타 HikariCP 설정
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setConnectionTimeout(10000); //커넥션 풀에서 새로운 커넥션을 가져올 때 최대 몇 ms까지 기다릴지를 설정 (단위: 밀리초)
        hikariConfig.setValidationTimeout(13000); //커넥션이 유효한지 테스트할 때 (connection.isValid()) 검증 쿼리가 완료될 때까지 기다릴 최대 시간 (단위: 밀리초)
        hikariConfig.setIdleTimeout(300000);      //풀에 있는 커넥션이 아무 작업도 하지 않고 대기(idle) 상태로 유지될 수 있는 최대 시간 (단위: 밀리초)
        hikariConfig.setMaxLifetime(600000);     // 커넥션이 생성된 후 존재할 수 있는 최대 시간 (단위: 밀리초) - 10분
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setInitializationFailTimeout(-1);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        session.setDataSource(dataSource);
        session.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
        session.setTypeAliasesPackage("com.adjh.springboot3vault.model");
        session.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/common-mybatis-config.xml"));
        return session.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
