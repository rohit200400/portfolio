package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.WorkExperienceRepository;
import com.rohit.portfolio.entity.UserDetail;
import com.rohit.portfolio.entity.WorkExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    @Autowired
    public WorkExperienceService(WorkExperienceRepository workExperienceRepository) {
        this.workExperienceRepository = workExperienceRepository;
    }

    /**
     * Saves a new WorkExperience object.
     *
     * @param workExperience The WorkExperience object to be saved.
     * @return The saved WorkExperience object.
     */
    public WorkExperience saveWorkExperience(WorkExperience workExperience) {
        return workExperienceRepository.save(workExperience);
    }

    /**
     * Retrieves a WorkExperience object by experience key.
     *
     * @param userDetail the UserId of the User.
     * @return An Optional containing the WorkExperience object if found, or an empty Optional if not found.
     */
    public Optional<List<WorkExperience>> getWorkExperienceByUserId(UserDetail userDetail) {
        return workExperienceRepository.getWorkExperienceByUserDetail(userDetail);
    }

    /**
     * Retrieves all WorkExperience objects.
     *
     * @return A list of all WorkExperience objects.
     */
    public List<WorkExperience> getAllWorkExperiences() {
        return workExperienceRepository.findAll();
    }

    /**
     * Deletes a WorkExperience object by experience key.
     *
     * @param expKey The experience key of the WorkExperience to be deleted.
     */
    public void deleteWorkExperience(Integer expKey) {
        workExperienceRepository.deleteById(expKey);
    }
}
