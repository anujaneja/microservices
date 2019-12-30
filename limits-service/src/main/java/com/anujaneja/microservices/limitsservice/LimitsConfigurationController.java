package com.anujaneja.microservices.limitsservice;

import com.anujaneja.microservices.limitsservice.config.Configuration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveConfigurations() {
        return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
    }

    @GetMapping("/limits-fault-example")
    @HystrixCommand(fallbackMethod = "returnFallbackDefaultConfig")
    public LimitConfiguration retrieveConfigurationsWithFallback() {
        throw new RuntimeException("");
    }

    public LimitConfiguration returnFallbackDefaultConfig() {
        return new LimitConfiguration(999999,9);
    }
}
