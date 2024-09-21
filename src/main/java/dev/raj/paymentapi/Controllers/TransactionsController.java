package dev.raj.paymentapi.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.raj.paymentapi.DAOs.Reports;
import dev.raj.paymentapi.DTOs.CurrencyDto;

import dev.raj.paymentapi.DTOs.ReportsDto;
import dev.raj.paymentapi.DTOs.ResponseDto;
import dev.raj.paymentapi.Models.Currency;
import dev.raj.paymentapi.Services.RateLimitService;
import dev.raj.paymentapi.Services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TransactionsController {


    @Autowired
    TransactionsService transactionsService;

    @Autowired
    RateLimitService rateLimitService;


    @PreAuthorize("hasAnyRole('ADMIN') and hasAnyAuthority('write','read')")
    @PostMapping("/transactions")
    public ResponseEntity<Currency> getTransactions(@RequestBody CurrencyDto currencyDto) throws JsonProcessingException {
        if(rateLimitService.tryConsumeTransaction()) {
            Currency currency = transactionsService.getTransactions(currencyDto);
            return new ResponseEntity<>(currency, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
    }


    @PreAuthorize("hasAnyRole('ADMIN') and hasAnyAuthority('write')")
    @PostMapping("/hello")
    public String getResponse(){
        return "hello";
    }



    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/reports")
    public double getTransactionReports(@RequestBody ReportsDto reportsDto){
       return transactionsService.getTransactionReports(reportsDto.getFrom(), reportsDto.getTo());
    }



    @PreAuthorize("hasAnyRole('ADMIN','USER') and hasAnyAuthority('read')")
    @GetMapping("/MontlyReports")
    public ResponseEntity<Reports> getMonthlyReports(){
        if(rateLimitService.tryConsumeReports()) {
            Reports reports = transactionsService.getMonthlyReports();
            return new ResponseEntity<>(reports, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER') and hasAnyAuthority('read')")
    @GetMapping("/WeeklyReports")
    public ResponseEntity<Reports> getWeeklyReports(){
        if(rateLimitService.tryConsumeReports()) {
            Reports reports= transactionsService.getWeeklyReports();
            return new ResponseEntity<>(reports, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER') and hasAnyAuthority('read')")
    @GetMapping("/YearlyReports")
    public ResponseEntity<Reports> getYearlyReports(){
        if(rateLimitService.tryConsumeReports()) {
            Reports reports= transactionsService.getYearlyReports();
            return new ResponseEntity<>(reports, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }
    }
 }
