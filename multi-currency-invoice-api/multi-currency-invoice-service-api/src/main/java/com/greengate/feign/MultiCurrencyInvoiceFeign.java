package com.greengate.feign;

import com.greengate.model.CurrencyRate;
import com.greengate.request.InvoiceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.greengate.constant.ApiConstant.PATH_EXCHANGE_RATE;
import static com.greengate.constant.ApiConstant.PATH_INVOICE_TOTAL;

@FeignClient(value = "multi-currency-invoice-service", contextId = "invoice-service")
public interface MultiCurrencyInvoiceFeign {
    @GetMapping(PATH_EXCHANGE_RATE)
    public @ResponseBody
    CurrencyRate getExchangeRate(@PathVariable Date date, String base, List<String> symbols);

    @PostMapping(PATH_INVOICE_TOTAL)
    public @ResponseBody
    Double getInvoiceTotalAmount(@RequestBody InvoiceRequest invoiceRequest);
}
