package ru.example.testcontainers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Container
    static GenericContainer<?> devContainer =
            new GenericContainer<>("devapp").withExposedPorts(8080);

    @Container
    static GenericContainer<?> prodContainer =
            new GenericContainer<>("prodapp").withExposedPorts(8081);

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    static void setUp() {
        devContainer.start();
        prodContainer.start();
    }

    @Test
    void testDevProfile() {
        int port = devContainer.getMappedPort(8080);
        ResponseEntity<String> response = restTemplate
                .getForEntity("http://localhost:" + port + "/", String.class);
        assertEquals("Current profile is dev", response.getBody().trim());
    }

    @Test
    void testProdProfile() {
        int port = prodContainer.getMappedPort(8081);
        ResponseEntity<String> response = restTemplate
                .getForEntity("http://localhost:" + port + "/", String.class);
        assertEquals("Current profile is production", response.getBody().trim());
    }
}
