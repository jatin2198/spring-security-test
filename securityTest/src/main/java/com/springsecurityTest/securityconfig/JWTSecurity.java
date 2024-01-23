package com.springsecurityTest.securityconfig;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springsecurityTest.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class JWTSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	JWTAuthenticationFilter jwtFilter;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		
		auth.userDetailsService(customUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http 
		.csrf().disable()
		.cors().disable()
	     .authorizeRequests()
	     ///.antMatchers("/home").permitAll()---->to permit all means user ID pass will not work for this
	    // .antMatchers("/home").hasRole("GUEST")
	    //.antMatchers("/user/**").hasRole("ADMIN")
	     .antMatchers("/token").permitAll()
	     .anyRequest()
	     .authenticated()
	     .and()
	     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
@Bean
	
	public PasswordEncoder password() {
		return NoOpPasswordEncoder.getInstance();}
		
		//return new BCryptPasswordEncoder(10);
	
	

@Bean
public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
	
	
}
}

