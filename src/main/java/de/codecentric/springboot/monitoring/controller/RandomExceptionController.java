package de.codecentric.springboot.monitoring.controller;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RandomExceptionController {

    private final GaugeService gaugeService;
    private final CounterService counterService;

    @Autowired
    public RandomExceptionController(final GaugeService gaugeService, final CounterService counterService) {
        this.gaugeService = gaugeService;
        this.counterService = counterService;
    }

    @RequestMapping(value = "/random/exception")
    public String randomException() {
        final double randomValue = new Random().nextDouble();
        this.gaugeService.submit("myRequestRandomValue", randomValue);
        if (randomValue > 0.5) {
            this.counterService.increment("myExceptionCounter");
            log.error("random exception was triggered");
            throw new IllegalStateException("random exception");
        }
        return "work well";
    }
}
