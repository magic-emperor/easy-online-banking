package com.web_banking_application.banking.dto;

public class AuthResponse {
    public AuthResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	private String jwt;

}