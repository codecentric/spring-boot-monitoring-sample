package de.codecentric.springboot.monitoring.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

@Component
public class CustomEndpointController implements Endpoint<List<String>> {

    @Override
    public String getId() {
        return "customEndpoint";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return true;
    }

    @Override
    public List<String> invoke() {
        // your custom logic
        List<String> messages = new ArrayList<>();
        messages.add("Message 1");
        messages.add("Message 2");
        return messages;
    }
}
