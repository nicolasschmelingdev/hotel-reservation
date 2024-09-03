package com.ntconsult.hotelreservation.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.Serializable;

@Configuration
public class LiquibaseConfig implements Serializable {

    @Value("${spring.datasource.liquibase.change-log}")
    private String changelogPath;

    @Bean(name = "liquibase")
    public SpringLiquibase liquibase(DataSource dataSource) {
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(changelogPath);
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}