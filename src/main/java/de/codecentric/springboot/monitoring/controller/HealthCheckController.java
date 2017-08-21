package de.codecentric.springboot.monitoring.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckController implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = specificHealthCheck();
        if (errorCode != 0) {
            return Health.down()
                    .withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

    public int specificHealthCheck() {
        // your logic to check health
        return 0;
    }
}
