package com.javabeans.in.service.booking.system.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.javabeans.in.service.booking.system.jwtservices.UserDetailsServiceImpl;
import com.javabeans.in.service.booking.system.security.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String token=null;
		String userName=null;
		
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			userName=jwtUtil.extractUsername(token);
		}
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userDetailsServiceImpl.loadUserByUsername(userName);
			if(jwtUtil.validationToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	

}
