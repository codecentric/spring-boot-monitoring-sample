package de.codecentric.springbootshowcase.controller;

import de.codecentric.springbootshowcase.domain.EchoMessage;
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

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GaugeService gaugeService;

    @Autowired
    private CounterService counterService;

    @RequestMapping("/echo")
    public String echo(@RequestParam(value = "text", defaultValue = "Hello World") final String text) {
        return String.format("Message %d: %s%n", counter.incrementAndGet(), text);
    }

    @RequestMapping(value = "/echo/json", method = RequestMethod.GET, produces = "application/json")
    public EchoMessage echoJson(@RequestParam(value = "text", defaultValue = "Hello World") final String text) {
        this.counterService.increment("myCounter");
        this.gaugeService.submit("myRequestTime", 4.4d);
        return new EchoMessage(text);
    }
}
