package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPhoneRepository extends JpaRepository<UserPhone, String> {
    @Query("SELECT ph FROM UserPhone ph WHERE ph.userId = :userId")
    Optional<List<UserPhone>> getUserPhoneByUserId(int userId);
}
