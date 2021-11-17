package com.microservice.report.client;

import com.microservice.report.config.ReportServiceConfig;
import com.microservice.report.entities.dtos.DocumentsDto;
import com.microservice.report.entities.dtos.ResponseAccountDto;
import com.microservice.report.entities.dtos.ResponseCustomerDto;
import com.microservice.report.entities.dtos.TransactionDto;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Component
public class AccountServiceClient {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private ReportServiceConfig config;

    public Optional<ResponseAccountDto> findAccountByAccountNumber(String accountNumber){
    	
        Optional<ResponseAccountDto> result = Optional.empty();
        try{
            result = Optional.ofNullable(restTemplate.getForObject(config.getAccountServiceUrl()+"/findByAccountNumber/{accountNumber}",ResponseAccountDto.class,accountNumber));
        }catch (HttpClientErrorException ex){
            if(ex.getStatusCode() != HttpStatus.NOT_FOUND){
                throw  ex;
            }
        }
        return result;
    }

    public List<ResponseAccountDto> findAccountByCustomersDnis(DocumentsDto customerDnis){
        List<ResponseAccountDto> result = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(customerDnis,headers);
        result =  restTemplate.exchange(config.getAccountServiceUrl()+"/customers",
                HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<ResponseAccountDto>>() {
                }).getBody();

        log.info("Response:" + requestEntity.getHeaders());
        return result;
    }

    public Optional<ResponseAccountDto> updateAmount(TransactionDto dto, String accountId){
        ResponseAccountDto result = new ResponseAccountDto();
        //Heders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //Content
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(dto,headers);
        result =  restTemplate.exchange(config.getAccountServiceUrl()+"/updateAmount/{accountId}",
                HttpMethod.PUT, requestEntity, ResponseAccountDto.class,accountId).getBody();

        log.info("Response:" + requestEntity.getHeaders());
        return Optional.ofNullable(result);
    }


}
