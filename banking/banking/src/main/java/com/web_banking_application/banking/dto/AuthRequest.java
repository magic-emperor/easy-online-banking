package com.web_banking_application.banking.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthRequest {
	private long userId;
    private String username;
    private String password;
	public String getUsername() {
		return username;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}