package com.rohit.portfolio.service;

import com.rohit.portfolio.entity.UserDetail;
import com.rohit.portfolio.entity.UserPhone;
import com.rohit.portfolio.dao.UserPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPhoneService {

    private final UserPhoneRepository userPhoneRepository;

    @Autowired
    public UserPhoneService(UserPhoneRepository userPhoneRepository) {
        this.userPhoneRepository = userPhoneRepository;
    }

    /**
     * Saves a new UserPhone object.
     *
     * @param userPhone The UserPhone object to be saved.
     * @return The saved UserPhone object.
     */
    public UserPhone saveUserPhone(UserPhone userPhone) {
        return userPhoneRepository.save(userPhone);
    }

    /**
     * Retrieves a UserPhone object by contact number.
     *
     * @param contactNumber The contact number of the UserPhone.
     * @return An Optional containing the UserPhone object if found, or an empty Optional if not found.
     */
    public Optional<UserPhone> getUserPhoneByNumber(String contactNumber) {
        return userPhoneRepository.findById(contactNumber);
    }

    /**
     * Retrieves all UserPhone objects by user id.
     *
     * @param userDetail The User ID of the User.
     * @return An Optional containing the list of all UserPhone object if found, or an empty Optional if not found.
     */
    public Optional<List<UserPhone>> getUserPhoneByUserId(UserDetail userDetail) {
        return userPhoneRepository.getUserPhoneByUserDetail(userDetail);
    }

    /**
     * Retrieves all UserPhone objects.
     *
     * @return A list of all UserPhone objects.
     */
    public List<UserPhone> getAllUserPhones() {
        return userPhoneRepository.findAll();
    }

    /**
     * Deletes a UserPhone object by contact number.
     *
     * @param contactNumber The contact number of the UserPhone to be deleted.
     */
    public void deleteUserPhone(String contactNumber) {
        userPhoneRepository.deleteById(contactNumber);
    }
}
