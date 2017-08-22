package de.codecentric.springboot.monitoring;

import static org.assertj.core.api.Assertions.assertThat;

import de.codecentric.springboot.monitoring.domain.EchoMessage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class EchoMessageJsonTest {

    private static final String MESSAGE = "Hello";

    @Autowired
    private JacksonTester<EchoMessage> json;

    @Test
    public void testSerialize() throws Exception {
        EchoMessage details = new EchoMessage(MESSAGE);
        assertThat(this.json.write(details)).isEqualToJson(getFile("expectedEchoMessage.json"));
        // or
        assertThat(this.json.write(details)).hasJsonPathStringValue("@.message");
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.message")
                .isEqualTo(MESSAGE);
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"message\":\"Hello\"}";
        assertThat(this.json.parse(content))
                .isEqualToComparingFieldByField(new EchoMessage(MESSAGE));
        assertThat(this.json.parseObject(content).getMessage()).isEqualTo(MESSAGE);
    }

    private String getFile(String fileName) {
        StringBuilder result = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (final Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                result.append(scanner.nextLine()).append("\n");
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }
}
