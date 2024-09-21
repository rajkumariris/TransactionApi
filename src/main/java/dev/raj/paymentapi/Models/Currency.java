package dev.raj.paymentapi.Models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document
@Data
public class Currency {
    @Id
    private String id;
    private String amount;
    private String currency;
    private String transactionType;
    private LocalDate transactionDate;
}
