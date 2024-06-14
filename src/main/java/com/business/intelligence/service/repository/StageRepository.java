package com.business.intelligence.service.repository;

import com.business.intelligence.service.model.constructionregion.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage, Integer> {
}
