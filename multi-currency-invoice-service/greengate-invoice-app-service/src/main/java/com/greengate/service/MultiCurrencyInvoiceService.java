package com.greengate.service;

import com.greengate.model.CurrencyRate;
import com.greengate.model.Invoice;
import com.greengate.model.PurchaseRecord;
import com.greengate.request.InvoiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.greengate.constant.FrankfurterApp.FRANKFURTER_APP_HOST;

@Component
@RequiredArgsConstructor
@Slf4j
public class MultiCurrencyInvoiceService {
    private final FrankfurterAppService frankfurterAppService;

    public Double getInvoiceTotalAmount(InvoiceRequest invoiceRequest) {
        Invoice invoice = invoiceRequest.getInvoice();
        String base = invoice.getCurrency();
        String date = invoice.getDate();
        List<PurchaseRecord> lines = invoice.getLines();
        List<String> symbols = invoice.getLines().stream().map(purchaseRecord -> purchaseRecord.getCurrency()).collect(Collectors.toList());
        CurrencyRate exchangeRate = getExchangeRate(date, base, symbols);
        Double totalAmount = calculateTotalAmount(exchangeRate, lines);
        return totalAmount;
    }

    private Double calculateTotalAmount(CurrencyRate exchangeRate, List<PurchaseRecord> lines) {
        BigDecimal sum = BigDecimal.valueOf(0.0);
        HashMap<String, Double> rates = exchangeRate.getRates();
        for (PurchaseRecord line : lines) {
            BigDecimal currency = BigDecimal.valueOf(rates.get(line.getCurrency())).setScale(4, RoundingMode.HALF_UP);
            BigDecimal amount = BigDecimal.valueOf(line.getAmount()).setScale(4, RoundingMode.HALF_UP);
            sum = sum.add(amount.divide(currency, 2, RoundingMode.HALF_UP));
        }
        return sum.doubleValue();
    }


    public CurrencyRate getExchangeRate(String date, String base, List<String> symbols) {
        Objects.requireNonNull(date, "query date can't be null");
        Objects.requireNonNull(base, "query date can't be null");
        Objects.requireNonNull(symbols, "query date can't be null");
        String symbolsParameters = symbolsToRequestParameters(symbols);
        String url = FRANKFURTER_APP_HOST + "/" + date + "?from=" + base + "&to=" + symbolsParameters;
        CurrencyRate currencyRate = frankfurterAppService.requestCurrencyRate(url, "GET");
        return currencyRate;
    }

    private String symbolsToRequestParameters(List<String> symbols) {
        return String.join(",", symbols);
    }
}
