package com.rohit.portfolio.service;

import com.rohit.portfolio.entity.UserDetail;
import com.rohit.portfolio.entity.UserPhone;
import com.rohit.portfolio.entity.UserSkill;
import com.rohit.portfolio.entity.WorkExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private UserPhoneService userPhoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserSkillService userSkillService;


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


    public ResponseEntity<List<Object>> getUserPortfolio(int id) {
        List<Object> finalResponse = new ArrayList<>();
        ResponseEntity<UserDetail> userDetail = userDetailService.getProfileData(id);
        if (userDetail.getStatusCode() == HttpStatus.OK) {
            finalResponse.add(userDetail.getBody());

            UserDetail user = userDetail.getBody();

            // Adding Address
            finalResponse.add(user.getAddress());

            // Getting the User Phone number
            Optional<List<UserPhone>> phones = userPhoneService.getUserPhoneByUserId(user);
            if(phones.isPresent()){
                finalResponse.add(phones.get());
            }
            else {
                finalResponse.add("No contact number of the user.");
            }

            // Getting the work experience
            Optional<List<WorkExperience>> we = workExperienceService.getWorkExperienceByUserId(userDetail.getBody());
            if(we.isPresent()){
                finalResponse.add(we.get());
            }
            else {
                finalResponse.add("No work experience for the user.");
            }

            // Getting the User Skills
            List<UserSkill> skills = userSkillService.getUserSkillByUserDetail(user);
            if(!skills.isEmpty()){
                finalResponse.add(skills);
            }
            else {
                finalResponse.add("No skills added for the user.");
           }


            return new ResponseEntity(finalResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("User Profile not found.",HttpStatus.NOT_FOUND);
    }
}

