package org.its.kfh.controller;

import org.its.kfh.model.AuthRequest;
import org.its.kfh.model.AuthResponse;
import org.its.kfh.service.MyUserDetailsService;
import org.its.kfh.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            //for authenticating the user creds
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        //fecthing the user name (from properties file)
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUsername());
        //generating the jwt token from utils class
        final String jwt = jwtUtils.generateToken(userDetails);
        //returning the jwt in the response with 200 OK
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
