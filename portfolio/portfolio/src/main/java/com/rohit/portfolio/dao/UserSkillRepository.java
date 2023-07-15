package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkill> {
}