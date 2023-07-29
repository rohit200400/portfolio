package com.rohit.portfolio.service;

import com.rohit.portfolio.entity.*;
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
    private SocialProfileService socialProfileService;
    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private UserPhoneService userPhoneService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private CareerSummaryService careerSummaryService;

    @Autowired
    private AwardsCertificationsService awardsCertificationsService;


    public ResponseEntity<List<Object>> getUserPortfolio(int id) {
        List<Object> finalResponse = new ArrayList<>();
        ResponseEntity<UserDetail> userDetail = userDetailService.getProfileData(id);
        if (userDetail.getStatusCode() == HttpStatus.OK) {
            // Adding user details
            finalResponse.add(userDetail.getBody());

            // crating an userDetail object for passing to other methods
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
            Optional<List<SocialProfile>> socialProfiles = socialProfileService.getSocialProfileByUserDetail(user);
            if(socialProfiles.isPresent()){
                finalResponse.add(socialProfiles.get());
            }
            else {
                finalResponse.add("No social profile for the user.");
            }

            // Getting Career Summary
            ResponseEntity<List<CareerSummary>> careerSummary = careerSummaryService.getCareerSummaryByUserDetail(user);
            if(careerSummary.getStatusCode() == HttpStatus.OK){
                finalResponse.add(careerSummary.getBody());
            }
            else if(careerSummary.getStatusCode() == HttpStatus.NOT_FOUND) {
                finalResponse.add("No career summary to show.");
            }

            // Getting the work experience
            Optional<List<WorkExperience>> we = workExperienceService.getWorkExperienceByUserId(user);
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

            // Getting projects
            ResponseEntity<List<Project>>  projects = projectsService.getProjectsByUserDetail(user);
            if(projects.getStatusCode() == HttpStatus.OK){
                finalResponse.add(projects.getBody());
            }
            else {
                finalResponse.add("No projects to retrieve for the user.");
            }

            // Getting Awards and Certification details
            ResponseEntity<List<AwardsCertifications>> awardCert = awardsCertificationsService.getAwardsCertificationByUserDetail(user);
            if(awardCert.getStatusCode() == HttpStatus.OK){
                finalResponse.add(awardCert.getBody());
            }
            else {
                finalResponse.add("No awards or certifications to retrieve for the user.");
            }

            return new ResponseEntity(finalResponse,HttpStatus.OK);
        }
        return new ResponseEntity("User Profile not found.",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Object>> updateProfileData(PersonRequest personRequest) {
        try {
            List<Object> output = new ArrayList<>();
            Address address = addressService.saveAddress(personRequest.getaddress()).getBody();
            System.out.println("Added address to the database");
            UserDetail newUser  = personRequest.getUserDetail();
            newUser.setAddress(address);
            output.add(userDetailService.updateProfileData(newUser).getBody());
            output.add(address);

            return new ResponseEntity<>(output, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Inside user service catch.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

