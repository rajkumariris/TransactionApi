package dev.raj.paymentapi.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReportsDto {

    private LocalDate from;
    private LocalDate to;
}
