package com.springsecurityTest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityTest.models.User;
import com.springsecurityTest.services.UserServices;

@RestController
@RequestMapping("/user")

public class UserController {
	
	@Autowired
	UserServices us;
	
	@GetMapping("/getAll")
///	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAll(){
		
		return us.getAllUser();
		
		
	}
	
	@GetMapping("user/{username}")
	public User getByuser(@PathVariable("username") String username) {
		return us.getbyUsername(username);
		
		
	}
	
	@PostMapping("/adduser")
	public User adduser(@RequestBody User user) {
		return us.addUser(user);
		
		
	}

}
