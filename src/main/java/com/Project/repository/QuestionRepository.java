package com.Project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Project.model.QuestionModel;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionModel, Integer>{
	
	List<QuestionModel> findByCategory(String category);
	
	@Query(value = "SELECT * FROM Question WHERE category = :category ORDER BY NEWID() OFFSET 0 ROWS FETCH NEXT :numQ ROWS ONLY", nativeQuery = true)
	List<QuestionModel> findRandomQuestionsByCategory(String category, int numQ);
	
}
