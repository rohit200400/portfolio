package com.rohit.portfolio.controller;

import com.rohit.portfolio.entity.PersonRequest;
import com.rohit.portfolio.service.ProfileService;
import com.rohit.portfolio.service.UserDetailService;
import com.rohit.portfolio.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<List<Object>> addUserDetail(@RequestBody PersonRequest personRequest){
        return profileService.updateProfileData(personRequest);
    }

    @GetMapping("/public/{userId}")
    public ResponseEntity<List<Object>> getPublicProfileData(@PathVariable("userId") int userId){
        return profileService.getUserPortfolio(userId);
    }



}
