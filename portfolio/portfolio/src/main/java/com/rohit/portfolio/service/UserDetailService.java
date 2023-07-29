package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.UserDetailsRepository;
import com.rohit.portfolio.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public ResponseEntity<UserDetail> getProfileData(int id) {
        Optional<UserDetail> userDetailOptional = userDetailsRepository.findById(id);
        if (userDetailOptional.isPresent()) {
            return new ResponseEntity<>(userDetailOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteProfileData(int id) {
        try {
            userDetailsRepository.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<UserDetail> updateProfileData(UserDetail user) {
        try {
            UserDetail newUser  = userDetailsRepository.save(user);
            System.out.println("created User");
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<UserDetail>> getAllUserDetails() {
        List<UserDetail> userDetailsList = userDetailsRepository.findAll();
        return new ResponseEntity<>(userDetailsList, HttpStatus.OK);
    }

    public ResponseEntity<UserDetail> getUserData(int id) {
        Optional<UserDetail> userDetailOptional = userDetailsRepository.findById(id);
        if (userDetailOptional.isPresent()) {
            return new ResponseEntity<>(userDetailOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
