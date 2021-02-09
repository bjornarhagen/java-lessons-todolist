package com.adstate.todoliste.controllers.todoliste;

import com.adstate.todoliste.TodolisteApplicationTests;
import com.adstate.todoliste.datatype.TodoListe;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class TestSecuredTodoListRestApi extends TodolisteApplicationTests {

  private TestRestTemplate restTemplate = new TestRestTemplate();

  private final String baseURL = "http://localhost:8385";
  private final Logger logger = LoggerFactory.getLogger(TodoListeControllerTest.class);
  private final String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDRUxJTkExNTA5IiwiZXhwIjoxNjEyOTEwNTQ5LCJpYXQiOjE2MTI4NzQ1NDl9.jJA93aj-RTTUvcf17RKWaT_dExqgCj9Iixr1LeqgqDM";

  @Test
  @DisplayName("Get All tasks controller")
  void testGetTodoListe() throws URISyntaxException {
    List<TodoListe> todoListes = new ArrayList<>();
    URI restPointUrl = new URI(createUrlWithPort("/todoliste/tasklist"));
    logger.info(" Get all todo task list .... ");
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(accessToken);
    HttpEntity<TodoListe> entity = new HttpEntity<>(headers);

    ResponseEntity<List<TodoListe>> responseEntity =
            restTemplate.exchange(restPointUrl,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<TodoListe>>() {
                    });

    todoListes = responseEntity.getBody();

    logger.info(" The current todo liste is: {} ", todoListes.size());
    Assert.assertEquals(200, responseEntity.getStatusCodeValue());
  }

  private String createUrlWithPort(String uri) {
    return baseURL + uri;
  }
}
