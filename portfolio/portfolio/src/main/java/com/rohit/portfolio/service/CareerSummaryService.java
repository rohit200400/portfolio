package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.CareerSummaryRepository;
import com.rohit.portfolio.entity.CareerSummary;
import com.rohit.portfolio.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareerSummaryService {

    private final CareerSummaryRepository careerSummaryRepository;

    @Autowired
    public CareerSummaryService(CareerSummaryRepository careerSummaryRepository) {
        this.careerSummaryRepository = careerSummaryRepository;
    }

    /**
     * Retrieves a career summary by its ID.
     *
     * @param summaryId The ID of the career summary to retrieve.
     * @return ResponseEntity containing the career summary if found, or NOT_FOUND status if not found.
     */
    public ResponseEntity<CareerSummary> getCareerSummary(int summaryId) {
        Optional<CareerSummary> careerSummaryOptional = careerSummaryRepository.findById(summaryId);
        if (careerSummaryOptional.isPresent()) {
            return new ResponseEntity<>(careerSummaryOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Retrieves a career summary by its ID.
     *
     * @param usedDetail The ID of the career summary to retrieve.
     * @return ResponseEntity containing the career summary if found, or NOT_FOUND status if not found.
     */
    public ResponseEntity<List<CareerSummary>> getCareerSummaryByUserDetail(UserDetail usedDetail) {
        Optional<List<CareerSummary>> careerSummaryOptional = careerSummaryRepository.getCareerSummaryByUserDetail(usedDetail);
        if (careerSummaryOptional.isPresent()) {
            return new ResponseEntity<>(careerSummaryOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes a career summary by its ID.
     *
     * @param summaryId The ID of the career summary to delete.
     * @return ResponseEntity with a success message if the career summary is deleted successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> deleteCareerSummary(int summaryId) {
        try {
            careerSummaryRepository.deleteById(summaryId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Saves a career summary.
     *
     * @param careerSummary The career summary to save.
     * @return ResponseEntity with a success message if the career summary is saved successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> saveCareerSummary(CareerSummary careerSummary) {
        try {
            careerSummaryRepository.save(careerSummary);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing career summary.
     *
     * @param careerSummary The updated career summary object.
     * @return ResponseEntity with a success message if the career summary is updated successfully,
     *         NOT_FOUND status if the career summary doesn't exist, or INTERNAL_SERVER_ERROR
     *         status with an error message if an exception occurs.
     */
    public ResponseEntity<String> updateCareerSummary(CareerSummary careerSummary) {
        try {
            Optional<CareerSummary> existingSummaryOptional = careerSummaryRepository.findById(careerSummary.getSummaryId());

            if (existingSummaryOptional.isPresent()) {
                CareerSummary existingSummary = existingSummaryOptional.get();
                // Update the existing career summary with the new data
                existingSummary.setObjective(careerSummary.getObjective());

                // Save the updated career summary
                careerSummaryRepository.save(existingSummary);
                return new ResponseEntity<>("Updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Career summary not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all career summaries.
     *
     * @return ResponseEntity containing a list of all career summaries if found,
     *         or an empty list if none exist.
     */
    public ResponseEntity<List<CareerSummary>> getAllCareerSummaries() {
        List<CareerSummary> careerSummaryList = careerSummaryRepository.findAll();
        return new ResponseEntity<>(careerSummaryList, HttpStatus.OK);
    }
}
