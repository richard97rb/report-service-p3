package com.microservice.report.controllers;

import com.microservice.report.entities.dtos.DocumentsDto;
import com.microservice.report.entities.dtos.SummaryAccountsDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.microservice.report.entities.dtos.ResponseReportDto;
import com.microservice.report.services.IReportService;

import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/reports")
public class ReportController {

	@Autowired
	IReportService reportService;
	
	@GetMapping("/latest-transactions/account/{accountNumber}")
	public Mono<ResponseReportDto> creditAndDebitCardTransactionReport(@PathVariable("accountNumber") String accountNumber) throws Exception{
		return Mono.just(reportService.creditAndDebitCardTransactionReport(accountNumber));
	}

	@PostMapping("accounts/summary")
	public Mono<SummaryAccountsDto> accountsSummary(@RequestBody DocumentsDto documentsDto) throws Exception{
		return Mono.just(reportService.findAccountsByCustomersDnis(documentsDto));
	}

	
	
}
