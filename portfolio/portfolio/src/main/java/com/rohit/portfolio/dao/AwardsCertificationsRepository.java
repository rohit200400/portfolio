package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.AwardsCertifications;
import com.rohit.portfolio.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AwardsCertificationsRepository extends JpaRepository<AwardsCertifications, Integer> {
    Optional<List<AwardsCertifications>> getAwardsCertificationByUserDetail(UserDetail userDetail);
}
