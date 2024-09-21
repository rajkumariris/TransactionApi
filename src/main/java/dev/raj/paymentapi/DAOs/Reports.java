package dev.raj.paymentapi.DAOs;

import dev.raj.paymentapi.DTOs.ReportsDto;
import dev.raj.paymentapi.Models.Currency;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Reports {
    List<Currency> reports;
    double totalCredited;
    double totalDebited;
    double netFlow;
}
