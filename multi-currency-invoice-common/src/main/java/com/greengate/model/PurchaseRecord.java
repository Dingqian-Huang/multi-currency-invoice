package com.greengate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseRecord implements Serializable {
    private String description;
    private String currency;
    private Double amount;
}
