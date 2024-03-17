package com.greengate.controller;

import com.greengate.model.CurrencyRate;
import com.greengate.request.InvoiceRequest;
import com.greengate.service.MultiCurrencyInvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.greengate.constant.ApiConstant.PATH_EXCHANGE_RATE;
import static com.greengate.constant.ApiConstant.PATH_INVOICE_TOTAL;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MultiCurrencyInvoiceController {

    private final MultiCurrencyInvoiceService multiCurrencyInvoiceService;

    @GetMapping(value = PATH_EXCHANGE_RATE)
    public @ResponseBody
    CurrencyRate getExchangeRate(@PathVariable String date, String base, String symbolsString) {
        List<String> symbols = Arrays.asList(symbolsString.split(","));
        return multiCurrencyInvoiceService.getExchangeRate(date, base, symbols);
    }

    @PostMapping(value = PATH_INVOICE_TOTAL, produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody
    ResponseEntity<String> getInvoiceTotalAmount(@RequestBody InvoiceRequest invoiceRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("plain/text"));
        return new ResponseEntity<>(multiCurrencyInvoiceService.getInvoiceTotalAmount(invoiceRequest).toString(), headers, HttpStatus.OK);
    }

}
