package com.Project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Project.model.QuestionModel;
import com.Project.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	QuestionRepository questionrepository;
	
	public List<QuestionModel> getAllQuestions() {
		try {
			return questionrepository.findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public QuestionModel getQuestionByID(int id) {
		try {
			return questionrepository.findById(id).orElse(new QuestionModel());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new QuestionModel();
	}
	
	public List<QuestionModel> getQuestionByCategory(String category) {
		try {
			questionrepository.findByCategory(category);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public void addQuestion(QuestionModel question) {
		questionrepository.save(question);
	}
	
	public void updateQuestion(QuestionModel question) {
		questionrepository.save(question);
	}
	
	public void deleteQuestion(int id) {
		questionrepository.deleteById(id);
	}
}
