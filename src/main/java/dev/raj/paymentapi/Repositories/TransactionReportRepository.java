package dev.raj.paymentapi.Repositories;

import dev.raj.paymentapi.Models.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionReportRepository extends MongoRepository<Currency, String> {

   @Query("{ 'transactionDate' : { $gte: ?0, $lte: ?1 } }")
   List<Currency> findByTransactionDateBetween(LocalDate from, LocalDate to);
}
