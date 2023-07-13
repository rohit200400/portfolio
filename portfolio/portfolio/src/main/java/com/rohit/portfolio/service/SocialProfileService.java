package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.SocialProfileRepository;
import com.rohit.portfolio.entity.SocialProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocialProfileService {

    private final SocialProfileRepository socialProfileRepository;

    @Autowired
    public SocialProfileService(SocialProfileRepository socialProfileRepository) {
        this.socialProfileRepository = socialProfileRepository;
    }

    public ResponseEntity<SocialProfile> getSocialProfileById(int userId, int mediaKey) {
        Optional<SocialProfile> socialProfileOptional = socialProfileRepository.findByIdandMediaKey(userId, mediaKey);
        if (socialProfileOptional.isPresent()) {
            return new ResponseEntity<>(socialProfileOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<SocialProfile> getSocialProfileByUserId(int userId) {
        Optional<SocialProfile> socialProfileOptional = socialProfileRepository.getSocialProfileByUserId(userId);
        if (socialProfileOptional.isPresent()) {
            return new ResponseEntity<>(socialProfileOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<SocialProfile>> getAllSocialProfiles() {
        List<SocialProfile> socialProfiles = socialProfileRepository.findAll();
        return new ResponseEntity<>(socialProfiles, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteSocialProfileById(int userId, int mediaKey) {
        try {
            socialProfileRepository.deleteById(userId, mediaKey);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> saveSocialProfile(SocialProfile socialProfile) {
        try {
            socialProfileRepository.save(socialProfile);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateSocialProfile(SocialProfile socialProfile) {
        try {
            Optional<SocialProfile> existingProfileOptional = socialProfileRepository.findByIdandMediaKey(socialProfile.getUserDetail().getUserId(), socialProfile.getSocialMedia().getMediaKey());

            if (existingProfileOptional.isPresent()) {
                SocialProfile existingProfile = existingProfileOptional.get();
                existingProfile.setProfileLink(socialProfile.getProfileLink());

                socialProfileRepository.save(existingProfile);
                return new ResponseEntity<>("Updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Social profile not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
