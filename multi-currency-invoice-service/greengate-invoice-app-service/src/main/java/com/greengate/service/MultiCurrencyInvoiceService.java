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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
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
        return calculateTotalAmount(lines, date, base);
    }


    /**
     * get lines's currency to get base rate
     * @param lines
     * @param date
     * @param base
     * @return
     */
    private Double calculateTotalAmount(List<PurchaseRecord> lines, String date, String base) {
        BigDecimal sum = BigDecimal.valueOf(0.0);
        for (PurchaseRecord purchaseRecord : lines) {
            HashMap<String, Double> rates = getExchangeRate(date, purchaseRecord.getCurrency(), Arrays.asList(base)).getRates();
            BigDecimal rate = BigDecimal.valueOf(rates.get(base)).setScale(4, RoundingMode.HALF_UP);
            BigDecimal lineAmount = rate.multiply(BigDecimal.valueOf(purchaseRecord.getAmount())).setScale(2, RoundingMode.HALF_UP);
            sum = sum.add(lineAmount);
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
