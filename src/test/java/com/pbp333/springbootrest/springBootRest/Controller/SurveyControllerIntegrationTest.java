package com.pbp333.springbootrest.springBootRest.Controller;

import com.pbp333.springbootrest.springBootRest.Model.Question;
import com.pbp333.springbootrest.springBootRest.SpringBootRestApplication;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    public void test(){

        String url ="http://localhost:"+port+"/surveys/Survey1/questions";

        ResponseEntity<String> response = responseCreation(url, HttpMethod.GET);

        System.out.println("Response: " + response.getBody());
    }

    @Test
    public void retrieveQuestionsForSurvey() {

        String url ="http://localhost:"+port+"/surveys/Survey1/questions";

        ResponseEntity<String> response = responseCreation(url, HttpMethod.GET);

        String correctResponse = "[{\"id\":\"Question1\",\"description\":\"Largest Country in the World\"," +
                "\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]},{\"id\"" +
                ":\"Question2\",\"description\":\"Most Populus Country in the World\",\"correctAnswer\":\"China\",\"" +
                "options\":[\"India\",\"Russia\",\"United States\",\"China\"]},{\"id\":\"Question3\",\"description\"" +
                ":\"Highest GDP in the World\",\"correctAnswer\":\"United States\",\"options\":[\"India\",\"Russia\",\"" +
                "United States\",\"China\"]},{\"id\":\"Question4\",\"description\":\"Second largest english speaking country\"" +
                ",\"correctAnswer\":\"India\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}]";

        try {
            JSONAssert.assertEquals(correctResponse, response.getBody(), false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void retrieveDetailsOfQuestion() {

        String url ="http://localhost:"+port+"/surveys/Survey1/questions/Question1";

        ResponseEntity<String> response = responseCreation(url, HttpMethod.GET);

        String correctResponse = "{\"id\":\"Question1\",\"description\":" +
                "\"Largest Country in the World\",\"correctAnswer\":\"Russia\"," +
                "\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";

        try {
            JSONAssert.assertEquals(correctResponse, response.getBody(), false);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void addQuestionToSurvey() {

        TestRestTemplate template = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        Question question = new Question("Doesn't matter", "smallest number", "1", Arrays.asList("1", "2", "3", "4"));

        ResponseEntity<String> response = template.exchange("http://localhost:"+port+"/surveys/Survey1/questions/",
                HttpMethod.POST, new HttpEntity<>(question, headers), String.class);

        assertThat(response.getHeaders().get(HttpHeaders.LOCATION).get(0), containsString("/surveys/Survey1/questions/"));


    }

    @Test
    public void getSurveys() {
    }

    @Test
    public void addSurvey() {
    }

    @Test
    public void updateSurvey() {
    }

    public ResponseEntity<String> responseCreation(String url, HttpMethod method){

        TestRestTemplate testRestTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity<String>(null, headers);

        return testRestTemplate.exchange(url, method, entity, String.class);

    }
}