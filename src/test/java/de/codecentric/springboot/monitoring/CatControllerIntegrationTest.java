package de.codecentric.springboot.monitoring;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CatControllerIntegrationTest {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenSendingRequestToController() {
        then(getResponseEntity().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnContentWhenSendingRequestToController() {
        then(getResponseEntity().getBody()).isEqualTo("Miau");
    }

    private ResponseEntity<String> getResponseEntity() {
        return this.testRestTemplate.getForEntity(
                "http://localhost:" + this.serverPort + "/cat", String.class);
    }

}
