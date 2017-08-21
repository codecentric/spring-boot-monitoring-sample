package de.codecentric.springboot.monitoring.controller;

import de.codecentric.springboot.monitoring.domain.EchoMessage;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    private final AtomicLong atomicCounter;
    private final GaugeService gaugeService;
    private final CounterService counterService;

    @Autowired
    public EchoController(final GaugeService gaugeService, final CounterService counterService) {
        this.gaugeService = gaugeService;
        this.counterService = counterService;
        this.atomicCounter = new AtomicLong();
    }

    @RequestMapping(value = "/echo")
    public String echo(@RequestParam(value = "text", defaultValue = "Hello World") final String text) {
        return getMessage(text).getMessage();
    }

    @RequestMapping(value = "/echo/json", method = RequestMethod.GET, produces = "application/json")
    public EchoMessage echoJson(@RequestParam(value = "text", defaultValue = "Hello World") final String text) {
        this.counterService.increment("myCounter");
        this.gaugeService.submit("myRequestTime", new Random().nextDouble());
        return getMessage(text);
    }

    private EchoMessage getMessage(final String text) {
        return new EchoMessage(String.format("%d: %s", atomicCounter.incrementAndGet(), text));
    }
}
