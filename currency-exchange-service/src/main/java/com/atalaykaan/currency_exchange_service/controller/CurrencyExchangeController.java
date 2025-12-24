package com.atalaykaan.currency_exchange_service.controller;

import com.atalaykaan.currency_exchange_service.model.CurrencyExchange;
import com.atalaykaan.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private CurrencyExchangeRepository currencyExchangeRepository;

    private Environment environment;

    public CurrencyExchangeController(CurrencyExchangeRepository currencyExchangeRepository, Environment environment) {

        this.currencyExchangeRepository = currencyExchangeRepository;
        this.environment = environment;
    }

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange exchangeCurrency(
            @PathVariable String from,
            @PathVariable String to
    ) {

        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);

        if(currencyExchange == null) {

            throw new RuntimeException("No exchange found");
        }

        String port = environment.getProperty("local.server.port");

        currencyExchange.setEnvironment(port);

        return currencyExchange;
    }
}
