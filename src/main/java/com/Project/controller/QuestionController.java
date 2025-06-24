package com.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Project.model.QuestionModel;
import com.Project.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionservice;
	
	@GetMapping("allquestions")
	public ResponseEntity<List<QuestionModel>> getAllQuestions() {
		List<QuestionModel> questions = questionservice.getAllQuestions();
		if(questions!=null) {
			return new ResponseEntity<>(questionservice.getAllQuestions(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("id/{id}")
	public QuestionModel getQuestionById(@PathVariable Integer id) {
		return  questionservice.getQuestionByID(id);
	}
	
	@GetMapping("category/{category}")
	public List<QuestionModel> getQuestionByCategory(@PathVariable String category) {
		return questionservice.getQuestionByCategory(category);
	}
	
	@PostMapping("addquestion")
	public void addQuestion(@RequestBody QuestionModel question) {
		questionservice.addQuestion(question);
	}
	
	@PutMapping("updatequestion")
	public void updateQuestion(@RequestBody QuestionModel question) {
		questionservice.updateQuestion(question);
	}
	
	@DeleteMapping("deletequestion/{id}")
	public void deleteQuestion(@PathVariable int id) {
		questionservice.deleteQuestion(id);
	}
}
