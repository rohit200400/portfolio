package com.rohit.portfolio.jwt;

import lombok.Data;

/**
 * @author Samson Effes
 */

@Data
public class JWTAuthenticationRequest {
    private String userName;
    private String password;
}
