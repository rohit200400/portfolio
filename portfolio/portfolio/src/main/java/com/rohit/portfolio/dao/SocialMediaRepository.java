package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMedia, Integer> {
    // You can add custom query methods or use the default methods provided by JpaRepository
}
