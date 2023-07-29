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
     * Checks is an address exist then Saves an address.
     *
     * @param address address row of the user.
     * @return ResponseEntity with an Address entity success message if the address is saved successfully,
     *         or INTERNAL_SERVER_ERROR status if an exception occurs.
     */
    public ResponseEntity<Address> saveAddress(Address address) {
        try {
            System.out.println("inside address service");
            Optional<Address> add = addressRepository.ifAddressExist(address);
            if(add.isEmpty())System.out.println("database does not contains the address");
            if(add.isPresent())System.out.println("database contains the address");
            if(add.isEmpty()){
                return new ResponseEntity<>(addressRepository.save(address), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(add.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
