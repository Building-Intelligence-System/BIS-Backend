package com.business.intelligence.service.repository;

import com.business.intelligence.service.model.constructionregion.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
