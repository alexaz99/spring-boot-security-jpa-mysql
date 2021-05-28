package com.alex.springbootsecurityjpamysql.service;

import com.alex.springbootsecurityjpamysql.models.MyUserDetails;
import com.alex.springbootsecurityjpamysql.models.User;
import com.alex.springbootsecurityjpamysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Create my implementation of UserDetailsService and implement
 * loadUserByUsername method that uses JPA to load users from mysql DB.
 *
 * Need to use JPA to get user info from DB
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        // first create a hard coded user by implemention UserDetails
        //return new MyUserDetails(s);
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + userName));

        // use a simple converter to convert to a Spring pre-defined UserDetails
        return new MyUserDetails(user.get());
    }
}
