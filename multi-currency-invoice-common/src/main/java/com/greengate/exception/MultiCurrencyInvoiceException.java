package com.greengate.exception;

public class MultiCurrencyInvoiceException extends Exception{
    private Integer code;
    private String message;

    public MultiCurrencyInvoiceException() {}

    public MultiCurrencyInvoiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
