package com.rohit.portfolio.dao;
import com.rohit.portfolio.entity.CareerSummary;
import com.rohit.portfolio.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareerSummaryRepository extends JpaRepository<CareerSummary, Integer> {
    Optional<List<CareerSummary>> getCareerSummaryByUserDetail(UserDetail usedDetail);
    // You can add custom query methods or use the default methods provided by JpaRepository
}
