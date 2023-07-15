package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.UserDetail;
import com.rohit.portfolio.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {
    Optional<List<WorkExperience>> getWorkExperienceByUserDetail(UserDetail userDetail);
}
