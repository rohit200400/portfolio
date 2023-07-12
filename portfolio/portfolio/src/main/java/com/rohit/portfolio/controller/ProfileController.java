package com.rohit.portfolio.controller;

import com.rohit.portfolio.entity.UserDetail;
import com.rohit.portfolio.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("rohit")
    public ResponseEntity<UserDetail> getProfileData(){
        return profileService.getProfileData(2);
    }

}
