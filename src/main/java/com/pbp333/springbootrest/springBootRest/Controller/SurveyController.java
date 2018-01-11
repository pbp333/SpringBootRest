package com.pbp333.springbootrest.springBootRest.Controller;

import com.pbp333.springbootrest.springBootRest.Model.Question;
import com.pbp333.springbootrest.springBootRest.Service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId){

       return surveyService.retrieveQuestions(surveyId);
    }
}
