package com.rohit.portfolio.entity;

import lombok.Data;

import java.util.Date;
@Data
public class PersonRequest {
    private String salutation;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String city;
    private String state;
    private String country;
    private String password;
    private String role;
    public UserDetail getUserDetail(){
        UserDetail newUser  = new UserDetail();
        newUser.setAddress(getaddress());
        newUser.setEmail(this.getEmail());
        newUser.setSalutation(this.getSalutation());
        newUser.setFirstName(this.getFirstName());
        newUser.setLastName(this.getLastName());
        newUser.setDateOfBirth(this.getDateOfBirth());
        newUser.setPassword(this.getPassword());
        newUser.setRoles(this.getRole());
        return newUser;
    }

    public Address getaddress(){
        Address newAddress = new Address();
        newAddress.setCity(city.toLowerCase());
        newAddress.setCountry(country.toLowerCase());
        newAddress.setState(state.toLowerCase());
        return newAddress;
    }
}
