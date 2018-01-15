package com.pbp333.springbootrest.springBootRest.Controller;

import com.pbp333.springbootrest.springBootRest.Model.Question;
import com.pbp333.springbootrest.springBootRest.Service.SurveyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SurveyController.class)
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Test
    public void retrieveDetailsForQuestion(){

        Question mockQuestion = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                "India", "Russia", "United States", "China"));

        Mockito.when(surveyService.retrieveQuestion(anyString(), anyString())).thenReturn(mockQuestion);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question1").accept(MediaType.APPLICATION_JSON);

        String expectedResponse = "{\"id\":\"Question1\",\"description\":" +
                "\"Largest Country in the World\",\"correctAnswer\":\"Russia\"," +
                "\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";
        try {
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void retrieveSurveyQuestion(){

        List<Question> mockList = Arrays.asList(new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                "India", "Russia", "United States", "China")), new Question("Question2",
                "Most Populus Country in the World", "China", Arrays.asList(
                "India", "Russia", "United States", "China")));

        Mockito.when(surveyService.retrieveQuestions(anyString())).thenReturn(mockList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions").accept(MediaType.APPLICATION_JSON);

        String expected = "[{\"id\":\"Question1\",\"description\":\"Largest Country in the World\"," +
                "\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]},{\"id\"" +
                ":\"Question2\",\"description\":\"Most Populus Country in the World\",\"correctAnswer\":\"China\",\"" +
                "options\":[\"India\",\"Russia\",\"United States\",\"China\"]}]";

        try {
            MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

            JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createSurveyQuestion(){

        Question mockQuestion = new Question("1",
                "Smallest Number", "1", Arrays.asList(
                "1", "2", "3", "4"));

        String question = "{\"id\":\"1\",\"description\":" +
                "\"Smallest Number\",\"correctAnswer\":\"1\"," +
                "\"options\":[\"1\",\"2\",\"3\",\"4\"]}";

        Mockito.when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class)));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/surveys/Survey1/questions").
                accept(MediaType.APPLICATION_JSON).content(question).contentType(MediaType.APPLICATION_JSON);

        try {
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();

            MockHttpServletResponse response = result.getResponse();

            assertEquals("http://localhost/surveys/Survey1/questions/1", response.getHeader(HttpHeaders.LOCATION));

            assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}