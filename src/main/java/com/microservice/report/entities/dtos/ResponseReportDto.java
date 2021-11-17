package com.microservice.report.entities.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseReportDto {
	
	String accountType;
	String accountNumber;
//	List<CustomerDto> customers; 
	List<String> associatedAccounts;
	List<TransactionDto> transactions;
	
}
