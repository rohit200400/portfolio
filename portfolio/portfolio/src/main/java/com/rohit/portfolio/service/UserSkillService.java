package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.UserSkillRepository;
import com.rohit.portfolio.entity.Skill;
import com.rohit.portfolio.entity.UserDetail;
import com.rohit.portfolio.entity.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSkillService {

    private final UserSkillRepository userSkillRepository;

    @Autowired
    public UserSkillService(UserSkillRepository userSkillRepository) {
        this.userSkillRepository = userSkillRepository;
    }

    /**
     * Saves a new UserSkill object.
     *
     * @param userSkill The UserSkill object to be saved.
     * @return The saved UserSkill object.
     */
    public UserSkill saveUserSkill(UserSkill userSkill) {
        return userSkillRepository.save(userSkill);
    }

    /**
     * Retrieves a UserSkill object by skill ID.
     *
     * @param skillId The skill ID of the Skill.
     * @param userId The user Id  of the User.
     * @return An Optional containing the UserSkill object if found, or an empty Optional if not found.
     */
    public Optional<UserSkill> getUserSkillById(Skill skillId, UserDetail userId) {
        UserSkill userSkill = new UserSkill(skillId, userId);
        return userSkillRepository.findById(userSkill);
    }

    /**
     * Retrieves all UserSkill objects.
     *
     * @return A list of all UserSkill objects.
     */
    public List<UserSkill> getAllUserSkills() {
        return userSkillRepository.findAll();
    }

    /**
     * Deletes a UserSkill object by skill ID.
     *
     * @param skillId The skill ID of the UserSkill to be deleted.
     */
    public void deleteUserSkill(Skill skillId, UserDetail userId) {
        UserSkill userSkill = new UserSkill(skillId, userId);
        userSkillRepository.deleteById(userSkill);
    }
}
