/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.configs;

import com.bookticket.service.JwtService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author ADMIN
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Loại bỏ phần "Bearer " để lấy mã thông báo
            Boolean rs = this.jwtService.validateTokenLogin(token);
            if (rs) {
                String userName = jwtService.getUsernameFromToken(token);
                // Thực hiện xác thực người dùng bằng mã thông báo ở đây
                UserDetails userDetails = userDetailService.loadUserByUsername(userName);
                if (userDetails != null) {
                    // Xác thực thành công, tiếp tục xử lý
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else {
                // Xác thực không thành công, xử lý theo ý muốn,
                // ví dụ như trả về lỗi 401 Unauthorized
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                return;
            }
        }

        // Tiếp tục chuyển request tới các filter tiếp theo trong chuỗi FilterChain
        filterChain.doFilter(request, response);
    }
}
