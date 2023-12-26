package org.its.kfh.utils;


import org.its.kfh.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

//I have taken refrence of this filter class from google
//i have added all comments below for understanding

//This filter will intercept all incoming requests,
//extracts jwt token
// and then validates it, and then allows/denies the request
@Component
public class IncomingRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String username = null;
        String jwt = null;
        final String authorizationHeader = request.getHeader("Authorization");

        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            //after "Bearer ", 7 chars the token will be there
            jwt = authorizationHeader.substring(7);
            //extract the username from jwt util
            username = jwtUtils.extractUsername(jwt);
        }

        if (Objects.nonNull(username)&& Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            //validating the token
            if (jwtUtils.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}