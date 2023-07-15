package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.UserDetail;
import com.rohit.portfolio.entity.UserSkill;
import com.rohit.portfolio.entity.UserSkillKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkillKey> {
    List<UserSkill> findByUserDetail(UserDetail userDetail);
}
