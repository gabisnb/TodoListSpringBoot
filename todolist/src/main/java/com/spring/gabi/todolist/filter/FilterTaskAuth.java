package com.spring.gabi.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.gabi.todolist.user.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        var servletPath = request.getServletPath();
        if(!servletPath.startsWith("/tasks/")){
            chain.doFilter(request, response);
            return;
        }
        
        var authorization = request.getHeader("Authorization");
        var code = authorization.substring("Basic".length()).trim();

        byte[] decoded = Base64.getDecoder().decode(code);
        var authString = new String(decoded);

        String [] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];

        var user = this.userRepository.findByUserName(username);
        if(user == null){
            response.sendError(401);
            return;
        }
        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword().toCharArray());
        if(!passwordVerify.verified){
            response.sendError(401);
            return;
        }
        request.setAttribute("userId", user.getId());
        chain.doFilter(request, response);
    }
    
}
