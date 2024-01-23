/*package com.springsecurityTest.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		
		
		http 
		.csrf().disable()
		     .authorizeRequests()
		     ///.antMatchers("/home").permitAll()---->to permit all means user ID pass will not work for this
		     .antMatchers("/home").hasRole("GUEST")
		    .antMatchers("/user/**").hasRole("ADMIN")
		     .anyRequest()
		     .authenticated()
		     .and()
		     .httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("jatin123").password(this.password().encode("jatin123")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("shivam").password(this.password().encode("shivam123")).roles("GUEST");
	}
	@Bean
	
	public PasswordEncoder password() {
		//return NoOpPasswordEncoder.getInstance();}
		
		return new BCryptPasswordEncoder(10);
	
	}
}
*/