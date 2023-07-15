package com.rohit.portfolio.service;

import com.rohit.portfolio.dao.AddressRepository;
import com.rohit.portfolio.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Retrieves an address by its ID.
     *
     * @param addressId The ID of the address to retrieve.
     * @return ResponseEntity containing the address if found, or NOT_FOUND status if not found.
     */
    public ResponseEntity<Address> getAddress(int addressId) {
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if (addressOptional.isPresent()) {
            return new ResponseEntity<>(addressOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    /**
     * Deletes an address by its ID.
     *
     * @param addressId The ID of the address to delete.
     * @return ResponseEntity with a success message if the address is deleted successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> deleteAddress(int addressId) {
        try {
            addressRepository.deleteById(addressId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Saves an address.
     *
     * @param address The address to save.
     * @return ResponseEntity with a success message if the address is saved successfully,
     *         or INTERNAL_SERVER_ERROR status with an error message if an exception occurs.
     */
    public ResponseEntity<String> saveAddress(Address address) {
        try {
            addressRepository.save(address);
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all addresses.
     *
     * @return ResponseEntity containing a list of all addresses if found, or an empty list if none exist.
     */
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addressList = addressRepository.findAll();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }
}
