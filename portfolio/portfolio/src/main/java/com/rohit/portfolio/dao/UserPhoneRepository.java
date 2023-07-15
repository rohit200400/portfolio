package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.UserDetail;
import com.rohit.portfolio.entity.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPhoneRepository extends JpaRepository<UserPhone, String> {
    Optional<List<UserPhone>> getUserPhoneByUserDetail(UserDetail userDetail);
}
