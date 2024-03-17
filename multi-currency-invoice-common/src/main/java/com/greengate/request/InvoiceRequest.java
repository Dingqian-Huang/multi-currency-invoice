package com.greengate.request;

import com.greengate.model.Invoice;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class InvoiceRequest implements Serializable {
    private Invoice invoice;
}
