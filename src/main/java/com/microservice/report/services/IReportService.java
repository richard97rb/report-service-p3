package com.microservice.report.services;

import com.microservice.report.entities.dtos.CustomerDto;
import com.microservice.report.entities.dtos.DocumentsDto;
import com.microservice.report.entities.dtos.ResponseReportDto;
import com.microservice.report.entities.dtos.SummaryAccountsDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface IReportService {

	ResponseReportDto creditAndDebitCardTransactionReport(String accountNumber) throws Exception;
	SummaryAccountsDto findAccountsByCustomersDnis(DocumentsDto customerDnis) throws Exception;
	
}
