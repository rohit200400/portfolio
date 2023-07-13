package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.SkillRepository;
import com.rohit.portfolio.entity.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    /**
     * Retrieves a skill by its ID.
     *
     * @param skillId The ID of the skill to retrieve.
     * @return ResponseEntity containing the skill if found, or NOT_FOUND status if not found.
     */
    public ResponseEntity<Skill> getSkill(int skillId) {
        Optional<Skill> skillOptional = skillRepository.findById(skillId);
        if (skillOptional.isPresent()) {
            return new ResponseEntity<>(skillOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes a skill by its ID.
     *
     * @param skillId The ID of the skill to delete.
     * @return ResponseEntity with a success message if the skill is deleted successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> deleteSkill(int skillId) {
        try {
            skillRepository.deleteById(skillId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Saves a skill.
     *
     * @param skill The skill to save.
     * @return ResponseEntity with a success message if the skill is saved successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> saveSkill(Skill skill) {
        try {
            skillRepository.save(skill);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing skill.
     *
     * @param skill The updated skill object.
     * @return ResponseEntity with a success message if the skill is updated successfully,
     *         NOT_FOUND status if the skill doesn't exist, or INTERNAL_SERVER_ERROR status
     *         with an error message if an exception occurs.
     */
    public ResponseEntity<String> updateSkill(Skill skill) {
        try {
            Optional<Skill> existingSkillOptional = skillRepository.findById(skill.getSkillId());

            if (existingSkillOptional.isPresent()) {
                Skill existingSkill = existingSkillOptional.get();
                // Update the existing skill with the new data
                existingSkill.setName(skill.getName());
                // Save the updated skill
                skillRepository.save(existingSkill);
                return new ResponseEntity<>("Updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all skills.
     *
     * @return ResponseEntity containing a list of all skills if found, or an empty list if none exist.
     */
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }
}
