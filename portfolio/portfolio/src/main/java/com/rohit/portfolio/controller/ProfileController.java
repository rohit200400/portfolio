package com.rohit.portfolio.controller;

import com.rohit.portfolio.entity.WorkExperience;
import com.rohit.portfolio.service.ProfileService;
import com.rohit.portfolio.service.UserDetailService;
import com.rohit.portfolio.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    WorkExperienceService workExperienceService;
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Object>> getProfileData(@PathVariable("userId") int userId){
        return profileService.getUserPortfolio(userId);
    }

    @GetMapping("workExp")
    public ResponseEntity<List<WorkExperience>> getWorkExperience(){
        return profileService.getWorkExperience(2);
    }



}
