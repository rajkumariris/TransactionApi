package dev.raj.paymentapi.Repositories;

import dev.raj.paymentapi.Models.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Currency, String> {

    public Currency save(Currency currency);
}
