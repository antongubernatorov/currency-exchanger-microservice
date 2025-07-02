package com.gubernatorov.currencyrateservice;

import com.gubernatorov.currencyrateservice.config.CurrencyClientCfg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CurrencyClientCfg.class)
public class CurrencyRateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyRateServiceApplication.class, args);
    }

}
