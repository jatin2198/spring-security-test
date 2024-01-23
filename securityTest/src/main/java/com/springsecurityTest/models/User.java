package com.springsecurityTest.models;

public class User {
	
	private String user;
	
	private String passwrd;
	
	private String email;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String user, String passwrd, String email) {
		super();
		this.user = user;
		this.passwrd = passwrd;
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswrd() {
		return passwrd;
	}

	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
