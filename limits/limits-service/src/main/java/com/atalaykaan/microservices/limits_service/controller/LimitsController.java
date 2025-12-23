package com.atalaykaan.microservices.limits_service.controller;

import com.atalaykaan.microservices.limits_service.bean.Limits;
import com.atalaykaan.microservices.limits_service.configuration.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    private Configuration configuration;

    public LimitsController(Configuration configuration) {

        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public Limits getLimits() {

        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
