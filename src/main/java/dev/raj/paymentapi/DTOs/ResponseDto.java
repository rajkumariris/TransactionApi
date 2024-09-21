package dev.raj.paymentapi.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class ResponseDto {
    public boolean success;
    public String terms;
    public String privacy;
    public int timestamp;
    public Date date;
    public String base;
    public Map<String,Double> rates;
}
