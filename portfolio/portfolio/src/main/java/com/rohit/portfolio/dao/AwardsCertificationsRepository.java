package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.AwardsCertifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardsCertificationsRepository extends JpaRepository<AwardsCertifications, Integer> {
    // You can add custom query methods or use the default methods provided by JpaRepository
}
