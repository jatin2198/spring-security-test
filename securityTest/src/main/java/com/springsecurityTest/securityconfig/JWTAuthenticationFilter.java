package com.springsecurityTest.securityconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springsecurityTest.jwtHelper.JwtUtil;
import com.springsecurityTest.services.CustomUserDetailsService;

@Component

public class JWTAuthenticationFilter  extends OncePerRequestFilter{
	
	@Autowired
	private CustomUserDetailsService cds;
	
	@Autowired
	private JwtUtil jwt_util;

	String username=null;
	String jwt_Token=null;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String requestHeader=request.getHeader("Authorization");
		
		
		
		
		
		if(requestHeader!=null&&requestHeader.startsWith("Bearer")) {
			
			jwt_Token=requestHeader.replaceAll("Bearer", "").trim();
			
			try {
				username=jwt_util.extractUsername(jwt_Token);
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
			UserDetails user_details=this.cds.loadUserByUsername(username);
			
			if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
				
				UsernamePasswordAuthenticationToken user_auth=	new UsernamePasswordAuthenticationToken(user_details,null,user_details.getAuthorities());
				user_auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(user_auth);
			
			}
			else {
				
				System.out.println("Not valid token");
			}
			
			
			
		}
		filterChain.doFilter(request, response);
	}
	
	//filterChain.doFilter(request, response);
}
