package com.greengate.kafka.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KafkaCurrencyRequest implements Serializable {
    private String requestId;
    private String date;
    private String base;
    private List<String> symbols;
}
