package com.ca.sample.integration;

import com.ca.sample.dto.VendingItemDto;
import com.ca.sample.utils.EntityCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class VendingIntegrationTest {

    @Value("${vending.machine.capacity}")
    private Long capacity;

    private VendingItemDto vendingItemDto;

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        vendingItemDto = EntityCreator.vendingItemDto();
    }

    @Test
    public void integrationTest() throws Exception {

        for (int i = 0; i < capacity; i++) {

            final ResponseEntity<String> responseEntity = deposit();
            assertEquals(200, responseEntity.getStatusCodeValue());
        }

        ResponseEntity<String> responseEntity = deposit();
        assertEquals(400, responseEntity.getStatusCodeValue());

        responseEntity = withdraw();
        assertEquals(200, responseEntity.getStatusCodeValue());

        responseEntity = deposit();
        assertEquals(200, responseEntity.getStatusCodeValue());

        final ResponseEntity<List> getlist = getlist();
        assertEquals(200, getlist.getStatusCodeValue());
        assertEquals(capacity.longValue(), getlist.getBody().size());
    }

    private ResponseEntity<String> deposit() throws JsonProcessingException {
        return post("/api/deposit");
    }

    private ResponseEntity<String> withdraw() throws JsonProcessingException {
        return post("/api/withdraw");
    }

    private ResponseEntity<String> post(final String uri) throws JsonProcessingException {

        try {

            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            final String json = new ObjectMapper().writeValueAsString(vendingItemDto);
            final HttpEntity<String> entity = new HttpEntity<>(json, headers);

            final String url = url(uri);
            return restTemplate.postForEntity(url, entity, String.class);

        } catch (HttpStatusCodeException e) {

            int statusCode = e.getStatusCode().value();
            return ResponseEntity.status(statusCode).build();
        }
    }

    private ResponseEntity<List> getlist() {

        final String url = url("/api/getlist");
        return restTemplate.getForEntity(url, List.class);
    }

    private String url(final String uri) {
        return "http://localhost:" + port + uri;
    }
}
