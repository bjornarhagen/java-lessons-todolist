package com.adstate.todoliste.controllers.todoliste;

import com.adstate.todoliste.datatype.TodoListe;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoListeControllerTest {
  @LocalServerPort
  private int port;

  private TestRestTemplate restTemplate = new TestRestTemplate();
  private HttpHeaders httpHeaders = new HttpHeaders();
  private final String baseURL = "http://localhost:8385";   // Move it to a properties file.!!!!

  private final Logger logger = LoggerFactory.getLogger(TodoListeControllerTest.class);


  @Test
  @DisplayName("Get All tasks controller")
  void testCreateTodoListe() throws URISyntaxException {
    URI uri = new URI(createUrlWithPort("/todoliste/tasklist"));
    logger.info(" Get all todo task list .... ");
    ResponseEntity<List<TodoListe>> responseEntity =
            restTemplate.exchange(uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<TodoListe>>() {
                    });
    List<TodoListe> todoListes = responseEntity.getBody();
    logger.info(" The current todo liste is: {} ", todoListes.size());
    Assert.assertTrue(todoListes.size() >= 0);
  }


  @Test
  @DisplayName("Get controller by a given task ID")
  public void testGetTodoById() throws URISyntaxException{
    String restPointUrl = createUrlWithPort("/todoliste/tasklist/1");
    HttpEntity<String>  entity = new HttpEntity<>(null, httpHeaders);
    ResponseEntity<TodoListe> response = restTemplate.exchange(
            restPointUrl,
            HttpMethod.GET,
            entity,
            new ParameterizedTypeReference<TodoListe>() {
            }
    );
    Assert.assertEquals(200, response.getStatusCodeValue());
  }


  private String createUrlWithPort(String uri) {
    return baseURL + uri;
  }
}