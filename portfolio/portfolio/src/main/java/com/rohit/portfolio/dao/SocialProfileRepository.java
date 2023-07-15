package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.SocialProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rohit.portfolio.entity.SocialProfile.SocialProfileIdPK;

import java.util.Optional;

@Repository
public interface SocialProfileRepository extends JpaRepository<SocialProfile, SocialProfileIdPK> {

    @Query("SELECT sp FROM SocialProfile sp WHERE sp.userDetail = :userId")
    Optional<SocialProfile> getSocialProfileByUserId(int userId);
}
