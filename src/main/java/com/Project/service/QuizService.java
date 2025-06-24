package com.Project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Project.model.QuestionModel;
import com.Project.model.QuestionWrapper;
import com.Project.model.QuizModel;
import com.Project.model.Response;
import com.Project.repository.QuestionRepository;
import com.Project.repository.QuizRepository;


@Service
public class QuizService {
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	private QuestionRepository questionRepository;


	public ResponseEntity<String> addQuiz(String category, int numQ, String title) {
		
		List<QuestionModel> questions = questionRepository.findRandomQuestionsByCategory(category,numQ);
		
		QuizModel quiz = new QuizModel();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizRepository.save(quiz);
		
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		Optional<QuizModel> quiz = quizRepository.findById(id);
		List<QuestionModel> questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		for(QuestionModel q : questionsFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionsForUser.add(qw);
		}
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		QuizModel quiz = quizRepository.findById(id).orElse(null);
	    if (quiz == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    List<QuestionModel> questions = quiz.getQuestions();
	    Map<Integer, String> correctAnswers = new HashMap<>();
	    for (QuestionModel q : questions) {
	        correctAnswers.put(q.getId(), q.getRightAnswer());
	    }

	    int right = 0;
	    for (Response response : responses) {
	        String correct = correctAnswers.get(response.getId());
	        if (correct != null && correct.equals(response.getResponse())) {
	            right++;
	        }
	    }

	    return new ResponseEntity<>(right, HttpStatus.OK);
	}
	
	
}
