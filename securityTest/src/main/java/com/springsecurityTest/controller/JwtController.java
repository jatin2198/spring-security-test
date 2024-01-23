package com.springsecurityTest.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityTest.jwtHelper.JwtUtil;
import com.springsecurityTest.models.JwtRequest;
import com.springsecurityTest.models.JwtResponse;
import com.springsecurityTest.services.CustomUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	private CustomUserDetailsService cutomerDetails;
	
	@Autowired
	private AuthenticationManager auth;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@RequestMapping(value="/token",method=RequestMethod.POST)
	public ResponseEntity<?> getToken(@RequestBody JwtRequest jwtreq) throws Exception{
		
		System.out.println(jwtreq);
		
		try {
			
			this.auth.authenticate(new UsernamePasswordAuthenticationToken(jwtreq.getUsername(), jwtreq.getPassword()));
			
		//auth.authenticate(new UsernamePasswordAuthenticationToken(jwtreq.getUsername(), jwtreq.getPassword(),Collections.EMPTY_LIST));
			
			//auth.authenticate()
			
		}catch (UsernameNotFoundException  e) {
			
			
			throw new UsernameNotFoundException("Bad Credentials");
			// TODO: handle exception
		}
		//return null;
		
		UserDetails user_details=this.cutomerDetails.loadUserByUsername(jwtreq.getUsername());
		
		String token=this.jwtutil.generateToken(user_details);
		//new to send in json format
		return  ResponseEntity.ok(new  JwtResponse(token));
		
		
	}

}
