package de.codecentric.springboot.monitoring.actuator;

import java.util.Arrays;
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
        return Arrays.asList("Message 1", "Message 2");
    }
}
