package com.pbp333.springbootrest.springBootRest.Controller;

import com.pbp333.springbootrest.springBootRest.Model.Question;
import com.pbp333.springbootrest.springBootRest.Model.Survey;
import com.pbp333.springbootrest.springBootRest.Service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId) {

        return surveyService.retrieveQuestions(surveyId);


    }

    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveDetailsOfQuestion(@PathVariable String surveyId, @PathVariable String questionId) {

        return surveyService.retrieveQuestion(surveyId, questionId);
    }

    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<?> addQuestionToSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion) {

        Question question = surveyService.addQuestion(surveyId, newQuestion);

        if (question == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(question.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/surveys")
    public List<Survey> getSurveys(){

        return surveyService.retrieveAllSurveys();
    }

    @PostMapping("/surveys")
    public ResponseEntity<?> addSurvey(@RequestBody Survey newSurvey){

        newSurvey.setId(surveyService.getNextId());

        Survey survey = surveyService.addSurvey(newSurvey);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(survey.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/surveys")
    public ResponseEntity<?> updateSurvey(@RequestBody Survey survey){

        surveyService.removeSurvey(survey.getId());

        surveyService.addSurvey(survey);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(survey.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
