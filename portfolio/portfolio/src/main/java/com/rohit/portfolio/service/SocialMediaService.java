package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.SocialMediaRepository;
import com.rohit.portfolio.entity.SocialMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocialMediaService {

    private final SocialMediaRepository socialMediaRepository;

    @Autowired
    public SocialMediaService(SocialMediaRepository socialMediaRepository) {
        this.socialMediaRepository = socialMediaRepository;
    }

    /**
     * Retrieves a social media entry by its media key.
     *
     * @param mediaKey The media key of the social media entry to retrieve.
     * @return ResponseEntity containing the social media entry if found, or NOT_FOUND status if not found.
     */
    public ResponseEntity<SocialMedia> getSocialMedia(int mediaKey) {
        Optional<SocialMedia> socialMediaOptional = socialMediaRepository.findById(mediaKey);
        if (socialMediaOptional.isPresent()) {
            return new ResponseEntity<>(socialMediaOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes a social media entry by its media key.
     *
     * @param mediaKey The media key of the social media entry to delete.
     * @return ResponseEntity with a success message if the social media entry is deleted successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> deleteSocialMedia(int mediaKey) {
        try {
            socialMediaRepository.deleteById(mediaKey);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Saves a social media entry.
     *
     * @param socialMedia The social media entry to save.
     * @return ResponseEntity with a success message if the social media entry is saved successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> saveSocialMedia(SocialMedia socialMedia) {
        try {
            socialMediaRepository.save(socialMedia);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing social media entry.
     *
     * @param socialMedia The updated social media entry.
     * @return ResponseEntity with a success message if the social media entry is updated successfully,
     *         NOT_FOUND status if the social media entry doesn't exist, or INTERNAL_SERVER_ERROR
     *         status with an error message if an exception occurs.
     */
    public ResponseEntity<String> updateSocialMedia(SocialMedia socialMedia) {
        try {
            Optional<SocialMedia> existingSocialMediaOptional = socialMediaRepository.findById(socialMedia.getMediaKey());
            if (existingSocialMediaOptional.isPresent()) {
                socialMediaRepository.save(socialMedia);
                return new ResponseEntity<>("Updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Social media entry not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all social media entries.
     *
     * @return ResponseEntity containing a list of all social media entries if found, or an empty list if none exist.
     */
    public ResponseEntity<List<SocialMedia>> getAllSocialMedia() {
        List<SocialMedia> socialMediaList = socialMediaRepository.findAll();
        return new ResponseEntity<>(socialMediaList, HttpStatus.OK);
    }
}
