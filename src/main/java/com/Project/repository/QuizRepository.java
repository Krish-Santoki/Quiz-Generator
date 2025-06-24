package com.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.model.QuizModel;

@Repository
public interface QuizRepository extends JpaRepository<QuizModel, Integer>{
	
}
