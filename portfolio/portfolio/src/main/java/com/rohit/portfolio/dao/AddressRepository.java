package com.rohit.portfolio.dao;

import com.rohit.portfolio.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    // You can add custom query methods or use the default methods provided by JpaRepository

    @Query("SELECT add FROM Address add WHERE add.city = :#{#address.city} AND add.state = :#{#address.state} AND add.country = :#{#address.country}")
    Optional<Address> ifAddressExist(Address address);
}
