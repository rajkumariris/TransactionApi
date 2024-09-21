package dev.raj.paymentapi.Controllers;

import dev.raj.paymentapi.DTOs.CurrencyDto;
import dev.raj.paymentapi.Models.Currency;
import dev.raj.paymentapi.Services.TransactionsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

//@WebMvcTest(TransactionsController.class)
class TransactionsControllerTest {
//
//    @Autowired
//    private MockMvc MockMvc;
//
//     @MockBean
//     private TransactionsService transactionsService;
//
//    @Test
//    void getTransactions() throws Exception {

//            Currency currency = new Currency();
//            currency.setAmount("100");
//            currency.setCurrency("USD");
//            CurrencyDto currencyDto = new CurrencyDto();
//            currencyDto.setAmount("100");
//            currencyDto.setCurrency("USD");
//
//            Mockito.when(transactionsService.getTransactions(currencyDto)).thenReturn(currency);
//
//            MockMvc.perform(post("/transactions")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content("{\"amount\":\"100\",\"currency\":\"USD\"}"))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.amount", is("100")))
//                    .andExpect(jsonPath("$.currency", is("USD")));
    //}
}