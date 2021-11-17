package com.microservice.report.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.modelmapper.ModelMapper;

@Getter
@Configuration
@PropertySource({"classpath:application.properties"})
public class ReportServiceConfig {

    @Value("${customerservice.url}")
    private String customerServiceUrl;
    
    @Value("${accountservice.url}")
    private String accountServiceUrl;

    @Value("${transactionservice.url}")
    private String transactionServiceUrl;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
