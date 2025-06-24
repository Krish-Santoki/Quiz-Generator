package com.Project.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Quiz")
public class QuizModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	@ManyToMany
	private List<QuestionModel> questions;

	public QuizModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizModel(int id, String title, List<QuestionModel> questions) {
		super();
		this.id = id;
		this.title = title;
		this.questions = questions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<QuestionModel> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}
	
	
}
