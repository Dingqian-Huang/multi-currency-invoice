package com.greengate.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greengate.exception.MultiCurrencyInvoiceException;
import com.greengate.model.CurrencyRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Slf4j
public class FrankfurterAppService {
    private Gson gson = new GsonBuilder().create();

    public CurrencyRate requestCurrencyRate(String strUrl, String method) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "PostmanRuntime/7.29.2");
            httpURLConnection.setRequestProperty("Accept", "*/*");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
            httpURLConnection.setRequestMethod(method);

            Integer responseCode = httpURLConnection.getResponseCode();
            String responseMessage = httpURLConnection.getResponseMessage();
            if (responseCode == 404) {
                throw new MultiCurrencyInvoiceException(responseCode, responseMessage);
            }

            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            httpURLConnection.disconnect();
            CurrencyRate currencyRate = gson.fromJson(stringBuffer.toString(), CurrencyRate.class);
            return currencyRate;
        } catch (Exception e) {
            log.error("error when request currency rate");
        }
        return null;
    }
}
