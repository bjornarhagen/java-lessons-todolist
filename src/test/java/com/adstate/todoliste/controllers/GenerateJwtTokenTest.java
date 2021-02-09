package com.adstate.todoliste.controllers;

import com.adstate.todoliste.datatype.AuthenticateRequest;
import com.adstate.todoliste.utils.JwtUtils;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class GenerateJwtTokenTest {

  private TestRestTemplate restTemplate = new TestRestTemplate();
  private HttpHeaders httpHeaders = new HttpHeaders();
  private final String baseURL = "http://localhost:8385";   // Move it to a properties file.!!!!
  private static JwtUtils jwtUtils = new JwtUtils();

  private final Logger logger = LoggerFactory.getLogger(GenerateJwtTokenTest.class);

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void testGenerateToken() throws URISyntaxException {
    logger.info("Generate an access token");
    URI restPointUrl = new URI(createUrlWithPort("/jwt/authenticate"));
    AuthenticateRequest authenticateRequest = AuthenticateRequest.builder()
            .userName("GENIEIT")
            .password("genieit")
            .build();

    HttpEntity<AuthenticateRequest> entity = new HttpEntity<>(authenticateRequest, httpHeaders);
    ResponseEntity<String> result = this.restTemplate.postForEntity(restPointUrl, entity, String.class);
    String accessToken = result.getBody();
    logger.info("\nGenerated access token: {}", accessToken);
    Assert.assertNotNull(accessToken);
  }

  private String createUrlWithPort(String uri) {
    return baseURL + uri;
  }
}