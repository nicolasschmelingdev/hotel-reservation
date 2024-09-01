package com.ntconsult.hotelreservation.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ApplicationProperties {

    @Value("${application.name}")
    private String applicationName;

    @Value("${application.version}")
    private String applicationVersion;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Value("${kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

    @Value("${kafka.topic.reservation}")
    private String kafkaReservationTopic;

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public String getDatasourceUsername() {
        return datasourceUsername;
    }

    public String getDatasourcePassword() {
        return datasourcePassword;
    }

    public String getKafkaBootstrapServers() {
        return kafkaBootstrapServers;
    }

    public String getKafkaReservationTopic() {
        return kafkaReservationTopic;
    }
}
