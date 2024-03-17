package com.greengate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrencyRate implements Serializable {
    private String base;
    private String date;
    private HashMap<String, Double> rates;
}
