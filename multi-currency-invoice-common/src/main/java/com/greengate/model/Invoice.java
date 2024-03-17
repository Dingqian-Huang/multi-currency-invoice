package com.greengate.model;

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
public class Invoice implements Serializable {
    private String currency;
    private String date;
    private List<PurchaseRecord> lines;
}
