package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.AwardsCertificationsRepository;
import com.rohit.portfolio.entity.AwardsCertifications;
import com.rohit.portfolio.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AwardsCertificationsService {

    private final AwardsCertificationsRepository awardsCertificationsRepository;

    @Autowired
    public AwardsCertificationsService(AwardsCertificationsRepository awardsCertificationsRepository) {
        this.awardsCertificationsRepository = awardsCertificationsRepository;
    }

    /**
     * Retrieves an awards certification by its ID.
     *
     * @param id The ID of the awards certification to retrieve.
     * @return ResponseEntity containing the awards certification if found, or NOT_FOUND status if not found.
     */
    public ResponseEntity<AwardsCertifications> getAwardsCertification(int id) {
        Optional<AwardsCertifications> awardsCertificationOptional = awardsCertificationsRepository.findById(id);
        if (awardsCertificationOptional.isPresent()) {
            return new ResponseEntity<>(awardsCertificationOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Retrieves an awards certification by UsedDetail.
     *
     * @param userDetail The ID of the awards certification to retrieve.
     * @return ResponseEntity containing the awards certification if found, or NOT_FOUND status if not found.
     */
    public ResponseEntity<List<AwardsCertifications>> getAwardsCertificationByUserDetail(UserDetail userDetail) {
        Optional<List<AwardsCertifications>> awardsCertificationOptional = awardsCertificationsRepository.getAwardsCertificationByUserDetail(userDetail);
        if (awardsCertificationOptional.isPresent()) {
            return new ResponseEntity<>(awardsCertificationOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes an awards certification by its ID.
     *
     * @param id The ID of the awards certification to delete.
     * @return ResponseEntity with a success message if the awards certification is deleted successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> deleteAwardsCertification(int id) {
        try {
            awardsCertificationsRepository.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Saves an awards certification.
     *
     * @param awardsCertification The awards certification to save.
     * @return ResponseEntity with a success message if the awards certification is saved successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> saveAwardsCertification(AwardsCertifications awardsCertification) {
        try {
            awardsCertificationsRepository.save(awardsCertification);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing awards certification.
     *
     * @param awardsCertification The updated awards certification object.
     * @return ResponseEntity with a success message if the awards certification is updated successfully,
     *         NOT_FOUND status if the awards certification doesn't exist, or INTERNAL_SERVER_ERROR
     *         status with an error message if an exception occurs.
     */
    public ResponseEntity<String> updateAwardsCertification(AwardsCertifications awardsCertification) {
        try {
            Optional<AwardsCertifications> existingCertificationOptional =
                    awardsCertificationsRepository.findById(awardsCertification.getId());

            if (existingCertificationOptional.isPresent()) {
                AwardsCertifications existingCertification = existingCertificationOptional.get();
                // Update the existing awards certification with the new data
                existingCertification.setName(awardsCertification.getName());
                existingCertification.setDescription(awardsCertification.getDescription());
                existingCertification.setRelatedLink(awardsCertification.getRelatedLink());
                existingCertification.setType(awardsCertification.getType());

                // Save the updated awards certification
                awardsCertificationsRepository.save(existingCertification);
                return new ResponseEntity<>("Updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Awards certification not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all awards certifications.
     *
     * @return ResponseEntity containing a list of all awards certifications if found,
     *         or an empty list if none exist.
     */
    public ResponseEntity<List<AwardsCertifications>> getAllAwardsCertifications() {
        List<AwardsCertifications> awardsCertificationsList = awardsCertificationsRepository.findAll();
        return new ResponseEntity<>(awardsCertificationsList, HttpStatus.OK);
    }
}
