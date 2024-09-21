package dev.raj.paymentapi.Repositories;

import dev.raj.paymentapi.Models.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Currency, String> {

    public Currency save(Currency currency);
}
