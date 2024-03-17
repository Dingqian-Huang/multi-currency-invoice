package com.greengate.kafka.response;

import com.greengate.kafka.request.KafkaCurrencyRequest;
import com.greengate.model.CurrencyRate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KafkaCurrencyResponse implements Serializable {
    private KafkaCurrencyRequest request;
    private Integer errorCode;
    private String errorMessage;
    private CurrencyRate currencyRate;
}
