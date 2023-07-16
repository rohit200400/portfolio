package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.SocialProfileRepository;
import com.rohit.portfolio.entity.SocialProfile;
import com.rohit.portfolio.entity.SocialProfile.SocialProfileIdPK;
import com.rohit.portfolio.entity.UserDetail;
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

    /**
     * Saves a new SocialProfile object.
     *
     * @param socialProfile The SocialProfile object to be saved.
     * @return The saved SocialProfile object.
     */
    public SocialProfile saveSocialProfile(SocialProfile socialProfile) {
        return socialProfileRepository.save(socialProfile);
    }

    /**
     * Retrieves a SocialProfile object by user ID and social media key.
     *
     * @param userDetail   The user ID of the SocialProfile.
     * @param socialMedia  The social media key of the SocialProfile.
     * @return An Optional containing the SocialProfile object if found, or an empty Optional if not found.
     */
    public Optional<SocialProfile> getSocialProfileById(Integer userDetail, Integer socialMedia) {
        SocialProfileIdPK socialProfileId = new SocialProfileIdPK(userDetail, socialMedia);
        return socialProfileRepository.findById(socialProfileId);
    }


    /**
     * Retrieves a SocialProfile object by user ID and social media key.
     *
     * @param userDetail   The user ID of the SocialProfile.
     * @return An Optional containing the List of SocialProfile object if found, or an empty Optional if not found.
     */
    public Optional<List<SocialProfile>> getSocialProfileByUserDetail(UserDetail userDetail) {
        return socialProfileRepository.getSocialProfileByUserDetail(userDetail);
    }

    /**
     * Retrieves all SocialProfile objects.
     *
     * @return A list of all SocialProfile objects.
     */
    public List<SocialProfile> getAllSocialProfiles() {
        return socialProfileRepository.findAll();
    }

    /**
     * Deletes a SocialProfile object by user ID and social media key.
     *
     * @param userId   The user ID of the SocialProfile to be deleted.
     * @param mediaKey  The social media key of the SocialProfile to be deleted.
     */
    public ResponseEntity<String> deleteSocialProfileById(int userId, int mediaKey) {
        try {
            SocialProfileIdPK socialProfileId = new SocialProfileIdPK(userId, mediaKey);
            Optional<SocialProfile> socialProfileOptional = socialProfileRepository.findById(socialProfileId);

            if (socialProfileOptional.isPresent()) {
                socialProfileRepository.delete(socialProfileOptional.get());
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Social profile not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
