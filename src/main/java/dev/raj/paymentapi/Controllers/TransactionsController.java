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

/**
 * This class is responsible for handling HTTP requests related to transactions.
 * It includes endpoints for creating, retrieving, updating, and deleting transactions.
 */
@RestController
public class TransactionsController {

    /**
     * The service that handles the business logic related to transactions.
     */
    @Autowired
    TransactionsService transactionsService;
    /**
     * The service that handles rate limiting for the API endpoints.
     */
    @Autowired
    RateLimitService rateLimitService;

    /**
     * This class is responsible for handling HTTP requests related to transactions.
     * It includes endpoints for creating, retrieving, updating, and deleting transactions.
     */


    /**
     * This method handles the request to create a new transaction.
     * It checks if the rate limit for transactions is not exceeded.
     * If the rate limit is not exceeded, it calls the TransactionsService to create a new transaction.
     * If the rate limit is exceeded, it returns a HTTP status code 429 (Too Many Requests).
     * This endpoint can only be accessed by users with the role 'ADMIN' and the authority 'WRITE' or 'READ-ONLY'.
     * @param currencyDto the data transfer object containing the details of the transaction to be created
     * @return ResponseEntity with the created transaction and HTTP status code
     * @throws JsonProcessingException if an error occurs during the processing of the JSON data
     */
    @PreAuthorize("hasAnyRole('ADMIN') and hasAnyAuthority('WRITE','READ-ONLY')")
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

    /**
     * This method handles the request to get transaction reports.
     * This endpoint can be accessed by users with the role 'ADMIN' or 'USER'.
     * @param reportsDto the data transfer object containing the details for generating the reports
     * @return the transaction reports
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/reports")
    public double getTransactionReports(@RequestBody ReportsDto reportsDto){
       return transactionsService.getTransactionReports(reportsDto.getFrom(), reportsDto.getTo());
    }



    /**
     * This method handles the request to get monthly reports.
     * It checks if the rate limit for reports is not exceeded.
     * If the rate limit is not exceeded, it calls the TransactionsService to get the monthly reports.
     * If the rate limit is exceeded, it returns a HTTP status code 429 (Too Many Requests).
     * This endpoint can be accessed by users with the role 'ADMIN' or 'USER' and the authority 'read'.
     * @return ResponseEntity with the monthly reports and HTTP status code
     */

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


    /**
     * This method handles the request to get weekly reports.
     * It checks if the rate limit for reports is not exceeded.
     * If the rate limit is not exceeded, it calls the TransactionsService to get the weekly reports.
     * If the rate limit is exceeded, it returns a HTTP status code 429 (Too Many Requests).
     * This endpoint can be accessed by users with the role 'ADMIN' or 'USER' and the authority 'read'.
     * @return ResponseEntity with the weekly reports and HTTP status code
     */
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

    /**
     * This method handles the request to get yearly reports.
     * It checks if the rate limit for reports is not exceeded.
     * If the rate limit is not exceeded, it calls the TransactionsService to get the yearly reports.
     * If the rate limit is exceeded, it returns a HTTP status code 429 (Too Many Requests).
     * This endpoint can be accessed by users with the role 'ADMIN' or 'USER' and the authority 'read'.
     * @return ResponseEntity with the yearly reports and HTTP status code
     */
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
