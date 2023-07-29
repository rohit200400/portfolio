package com.rohit.portfolio.security;

import com.rohit.portfolio.dao.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Samson Effes
 */

@Component
public class portfolioUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(portfolioUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
}