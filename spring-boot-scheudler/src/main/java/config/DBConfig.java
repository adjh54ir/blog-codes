package config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
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
    final
    ApplicationContext applicationContext;

    public DBConfig(ApplicationContext ac) {
        this.applicationContext = ac;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        session.setDataSource(dataSource);
        session.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
        session.setTypeAliasesPackage("com.adjh.multiflex.scheduler.model");
        session.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/common-mybatis-config.xml"));
        return session.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}