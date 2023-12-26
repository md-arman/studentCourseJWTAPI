package org.its.kfh.service;

import org.its.kfh.model.StudentDetails;
import org.its.kfh.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    //reading auth user name and password from properties file
    //this can also be read from DB for any user/student(with new fields)

    @Value("${jwt.user.name}")
    private String username;
    @Value("${jwt.user.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User(this.username, this.password, new ArrayList<>());
    }
}
