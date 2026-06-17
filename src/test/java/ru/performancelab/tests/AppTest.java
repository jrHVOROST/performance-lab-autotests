package ru.performancelab.tests;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    public void testKafkaStringIsNotEmpty() {
        String testString = "Kafka";
        
        // Используем AssertJ для проверки строки
        assertThat(testString)
            .as("Строка должна быть не пустой")
            .isNotEmpty()
            .isEqualTo("Kafka");
    }
}
