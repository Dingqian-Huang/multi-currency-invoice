package com.greengate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.greengate.feign")
public class MultiCurrencyInvoiceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiCurrencyInvoiceServiceApplication.class, args);
    }
}
