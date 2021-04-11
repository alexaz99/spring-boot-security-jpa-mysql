package com.alex.springbootsecurityjpamysql;

import com.alex.springbootsecurityjpamysql.models.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Create my implementation of UserDetailsService and implement
 * loadUserByUsername method that uses JPA to load users from mysql DB.
 *
 * Need to use JPA to get user info from DB
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // first create a hard coded user by implemention UserDetails
        return new MyUserDetails(s);
    }
}
