package com.springsecurityTest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springsecurityTest.models.User;

@Service
public class UserServices {
	
	List<User> list=new ArrayList<>();

	public UserServices() {
		
		list.add(new User("jatin", "jatin@123", "jatin1@gmail.com"));
		list.add(new User("jatin1", "jatin@121", "jatin2@gmail.com"));
		list.add(new User("jatin2", "jatin@122", "jatin3@gmail.com"));
		list.add(new User("jatin3", "jatin@123", "jatin4@gmail.com"));
		list.add(new User("jatin4", "jatin@124", "jatin5@gmail.com"));
	}
	
	public List<User> getAllUser() {
		return list;
		
		
	}
	
	
	public User getbyUsername(String name) {
		
	User u= list.stream().filter(i->i.getUser().equals(name)).findAny().get();
	
	return u;
		
		
	}
	
	
	public User addUser(User u) {
		
		list.add(u);
		return u;
		
		
	}

}
