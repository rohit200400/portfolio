package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.Project;
import com.rohit.portfolio.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Integer> {
    // You can add custom query methods or use the default methods provided by JpaRepository
    List<Project> getProjectByUserDetail(UserDetail userDetail);
}
