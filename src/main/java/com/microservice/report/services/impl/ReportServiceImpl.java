package com.microservice.report.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.microservice.report.entities.dtos.*;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.report.client.AccountServiceClient;
import com.microservice.report.client.CustomerServiceClient;
import com.microservice.report.client.TransactionServiceClient;
import com.microservice.report.repositories.IReportRepository;
import com.microservice.report.services.IReportService;

@Service
public class ReportServiceImpl implements IReportService {

	@Autowired
	private CustomerServiceClient customerClient;

	@Autowired
	private TransactionServiceClient transactionClient;

	@Autowired
	private AccountServiceClient accountClient;

	@Autowired
	private IReportRepository reportRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	@Override
	public ResponseReportDto creditAndDebitCardTransactionReport(String accountNumber) throws Exception {

		ResponseAccountDto account = accountClient.findAccountByAccountNumber(accountNumber).get();

		if (account.getAccountType().getType().equals("TARJETA_CREDITO")
				|| account.getAccountType().getType().equals("TARJETA_DEBITO")) {
			
			List<TransactionDto> transactions = transactionClient.findTransactionsByAccountId(account.get_id()).stream()
					.sorted(Comparator.comparing(TransactionDto::getTransactionDate).reversed()).limit(10)
					.collect(Collectors.toList());

			ResponseReportDto response = ResponseReportDto.builder().accountType(account.getAccountType().getType())
					.accountNumber(account.getAccountNumber()).associatedAccounts(account.getAccountsIds())
					.transactions(transactions).build();

			return response;
		} else {
			throw new Exception("Está función solo esta disponible para tarjetas de débito o crédito.");
		}

	}

	@Override
	public SummaryAccountsDto findAccountsByCustomersDnis(DocumentsDto customersDnis) throws Exception {

		List<ResponseAccountDto> accounts = accountClient.findAccountByCustomersDnis(customersDnis);
		if (accounts.size() > 0){
			SummaryAccountsDto response = SummaryAccountsDto.builder()
				.customersIds(accounts.get(0).getCustomersIds())
				.accounts(accounts)
				.build();
			return response;
		} else {
			throw new Exception("No existen productos de este cliente");
		}

	}



}
