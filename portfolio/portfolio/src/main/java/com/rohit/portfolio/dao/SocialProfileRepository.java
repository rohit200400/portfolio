package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.SocialProfile;
import com.rohit.portfolio.entity.SocialProfile.SocialProfileIdPK;
import com.rohit.portfolio.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocialProfileRepository extends JpaRepository<SocialProfile, SocialProfileIdPK> {

    Optional<List<SocialProfile>> getSocialProfileByUserDetail(UserDetail userDetail);
}
