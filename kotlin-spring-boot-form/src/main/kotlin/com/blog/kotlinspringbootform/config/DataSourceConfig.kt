package com.blog.kotlinspringbootform.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.type.JdbcType
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import javax.sql.DataSource

@Configuration
@PropertySource("classpath:/application.properties")
class DataSourceConfig @Autowired constructor(private var applicationContext: ApplicationContext) {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun hikariConfig(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    fun dataSource(): DataSource {
        return HikariDataSource(hikariConfig())
    }

    @Bean
    @Throws(Exception::class)
    fun sqlSessionFactory(dataSource: DataSource?): SqlSessionFactory? {
        val session = SqlSessionFactoryBean()
        session.setDataSource(dataSource)
        session.setMapperLocations(*applicationContext.getResources("classpath:mapper/*.xml"))
        session.setTypeAliasesPackage("com.blog.kotlinspringbootform.dto")


//        session.setConfigLocation(PathMatchingResourcePatternResolver().getResource("classpath:config/common-mybatis-config.xml"))
        val configuration = org.apache.ibatis.session.Configuration()
        configuration.isMapUnderscoreToCamelCase = true
        configuration.isCallSettersOnNulls = true
        configuration.jdbcTypeForNull = JdbcType.NULL
        session.setConfiguration(configuration)

        return session.getObject()
    }

    @Bean
    fun sqlSessionTemplate(sqlSessionFactory: SqlSessionFactory): SqlSessionTemplate {
        return SqlSessionTemplate(sqlSessionFactory)
    }
}