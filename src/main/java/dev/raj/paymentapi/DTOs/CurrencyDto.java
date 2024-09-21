package dev.raj.paymentapi.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CurrencyDto {
    private String id;
    private String amount;
    private String currency;
    private String transactionType;

}

