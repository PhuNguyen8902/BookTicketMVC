/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.configs;

import com.bookticket.service.JwtService;
import com.bookticket.service.UserService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author ADMIN
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

//    @Autowired
//  AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailService;

//    private final static String TOKEN_HEADER = "authorization";

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String authToken = httpRequest.getHeader(TOKEN_HEADER);
//        if (jwtService.validateTokenLogin(authToken)) {
//            String userName = jwtService.getUsernameFromToken(authToken);
//            UserDetails user = userDetailService.loadUserByUsername(userName);
//            System.out.println("---------------------config day");
//
//            System.out.println(user);
//
//            if (user != null) {
////                boolean enabled = true;
////                boolean accountNonExpired = true;
////                boolean credentialsNonExpired = true;
////                boolean accountNonLocked = true;
//
//                System.out.println(user.getAuthorities());
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
//                        null, user.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
////                Authentication authenticatedUser = authenticationManager.authenticate(authentication);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        chain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         HttpServletRequest httpRequest = (HttpServletRequest) request;
                             System.out.println(request);

        String authToken = httpRequest.getHeader("Authorization").substring(7);
                    System.out.println(authToken);

        if (jwtService.validateTokenLogin(authToken)) {
            String userName = jwtService.getUsernameFromToken(authToken);
            UserDetails user = userDetailService.loadUserByUsername(userName);
            System.out.println("---------------------config day");

            System.out.println(user);

            if (user != null) {
//                boolean enabled = true;
//                boolean accountNonExpired = true;
//                boolean credentialsNonExpired = true;
//                boolean accountNonLocked = true;

                System.out.println(user.getAuthorities());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                        null, user.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
//                Authentication authenticatedUser = authenticationManager.authenticate(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
