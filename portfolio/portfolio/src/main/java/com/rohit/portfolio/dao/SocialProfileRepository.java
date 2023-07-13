package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.SocialProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialProfileRepository extends JpaRepository<SocialProfile, Integer> {

    @Query("SELECT sp FROM SocialProfile sp WHERE sp.userId = :userId")
    Optional<SocialProfile> getSocialProfileByUserId(int userId);


    void deleteById(int userId, int mediaKey);
    @Query("SELECT sp FROM SocialProfile sp WHERE sp.userId = :userId AND sp.mediaKey = :mediaKey")
    Optional<SocialProfile> findByIdandMediaKey(int userId, int mediaKey);
}
