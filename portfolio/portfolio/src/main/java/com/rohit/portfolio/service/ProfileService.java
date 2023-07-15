package com.rohit.portfolio.service;

import com.rohit.portfolio.entity.UserDetail;
import com.rohit.portfolio.entity.WorkExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private WorkExperienceService workExperienceService;


    public ResponseEntity<List<WorkExperience>> getWorkExperience(int id) {
        ResponseEntity<UserDetail> userDetail = userDetailService.getProfileData(id);
        if (userDetail.getStatusCode() == HttpStatus.OK) {
            Optional<List<WorkExperience>> we = workExperienceService.getWorkExperienceByUserId(userDetail.getBody());
            if(we.isPresent()){
                return new ResponseEntity<>(we.get(), HttpStatus.OK);
            }
            return new ResponseEntity("Work experience not found.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("User Profile not found.",HttpStatus.NOT_FOUND);
    }
}
