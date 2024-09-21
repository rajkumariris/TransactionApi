package dev.raj.paymentapi.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.raj.paymentapi.Config.RestTemplateConfig;
import dev.raj.paymentapi.DAOs.Reports;
import dev.raj.paymentapi.DTOs.CurrencyDto;
import dev.raj.paymentapi.DTOs.ResponseDto;
import dev.raj.paymentapi.Models.Currency;
import dev.raj.paymentapi.Repositories.TransactionReportRepository;
import dev.raj.paymentapi.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionsService {

        @Autowired
        TransactionRepository transactionRepository;

        @Autowired
        TransactionReportRepository transactionReportRepository;

        @Autowired
        RestTemplate restTemplate;

        @Autowired
        RedisService redisService;
        public ResponseDto getResponse() throws JsonProcessingException {

            ResponseDto responseDto =  restTemplate.getForObject("https://api.fxratesapi.com/latest", ResponseDto.class);
            redisService.set("apiResponse", responseDto, 2L);
            System.out.println("api called");
            return responseDto;
        }

        public Currency getTransactions( CurrencyDto CurrencyDto) throws JsonProcessingException {
            ResponseDto responseDto;
            ResponseDto response= redisService.get("apiResponse", ResponseDto.class);
            if(response!=null){
                System.out.println("api called from if");
                responseDto = response;
            }
           else{
                System.out.println("api called from else");
                 responseDto = getResponse();
            }


            Double convertedAmount[] = new Double[]{0.0};
            responseDto.getRates().forEach((k, v) -> {
                        if (k.equals(CurrencyDto.getCurrency())) {
                            Double currencyVal = responseDto.getRates().get(k);
                            convertedAmount[0] = Integer.parseInt(CurrencyDto.getAmount()) / currencyVal;
                        }
                    }
            );
            Double creditAmount = convertedAmount[0];
            Currency currency1 = new Currency();
            currency1.setAmount(creditAmount.toString());
            currency1.setCurrency("USD");
            currency1.setTransactionType(CurrencyDto.getTransactionType());
            currency1.setTransactionDate(LocalDate.now());
            transactionRepository.save(currency1);
            return currency1;
        }

        public double getTransactionReports(LocalDate from, LocalDate to){
//            LocalDateTime fromDateTime = from;
//            LocalDateTime toDateTime = to;
           List<Currency> allTransactions = transactionReportRepository.findByTransactionDateBetween(from, to);
           double []sum = new double[1];
           allTransactions.forEach(currency -> {
               if(currency.getTransactionType().equals("credit")) {
                   sum[0] = sum[0] + Double.parseDouble(currency.getAmount());
               }
           });


           double totalCredit = sum[0];
           return totalCredit;
        }
        public Reports getMonthlyReports(){
            LocalDate from = LocalDate.now().minusMonths(1);
            LocalDate to = LocalDate.now();
            List<Currency> montlyTransaction=  transactionReportRepository.findByTransactionDateBetween(from, to);
            double []creditsum = new double[1];
            double []debitsum = new double[1];
            montlyTransaction.forEach(currency -> {
                if(currency.getTransactionType().equals("credit")) {
                    creditsum[0] = creditsum[0] + Double.parseDouble(currency.getAmount());
                }
                else if(currency.getTransactionType().equals("debit")){
                    debitsum[0] = debitsum[0] - Double.parseDouble(currency.getAmount());
                }
            });
            double totalCredit = creditsum[0];
            double totalDebit = debitsum[0];
            Reports reports = new Reports();
            reports.setReports(montlyTransaction);
            reports.setTotalCredited(totalCredit);
            reports.setTotalDebited(totalDebit);
            return reports;
        }
    public Reports getWeeklyReports(){
        LocalDate from = LocalDate.now().minusWeeks(1);
        LocalDate to = LocalDate.now();
        List<Currency> montlyTransaction=  transactionReportRepository.findByTransactionDateBetween(from, to);
        double []creditsum = new double[1];
        double []debitsum = new double[1];
        montlyTransaction.forEach(currency -> {
            if(currency.getTransactionType().equals("credit")) {
                creditsum[0] = creditsum[0] + Double.parseDouble(currency.getAmount());
            }
            else if(currency.getTransactionType().equals("debit")){
                debitsum[0] = debitsum[0] - Double.parseDouble(currency.getAmount());
            }
        });
        double totalCredit = creditsum[0];
        double totalDebit = debitsum[0];
        Reports reports = new Reports();
        reports.setReports(montlyTransaction);
        reports.setTotalCredited(totalCredit);
        reports.setTotalDebited(totalDebit);
        return reports;
    }
    public Reports getYearlyReports(){
        LocalDate from = LocalDate.now().minusYears(1);
        LocalDate to = LocalDate.now();
        List<Currency> montlyTransaction=  transactionReportRepository.findByTransactionDateBetween(from, to);
        double []creditsum = new double[1];
        double []debitsum = new double[1];
        montlyTransaction.forEach(currency -> {
            if(currency.getTransactionType().equals("credit")) {
                creditsum[0] = creditsum[0] + Double.parseDouble(currency.getAmount());
            }
            else if(currency.getTransactionType().equals("debit")){
                debitsum[0] = debitsum[0] - Double.parseDouble(currency.getAmount());
            }
        });
        double totalCredit = creditsum[0];
        double totalDebit = debitsum[0];
        Reports reports = new Reports();
        reports.setReports(montlyTransaction);
        reports.setTotalCredited(totalCredit);
        reports.setTotalDebited(totalDebit);
        return reports;
    }
}
