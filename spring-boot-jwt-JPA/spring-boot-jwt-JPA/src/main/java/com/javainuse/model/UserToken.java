package com.javainuse.model;

import org.springframework.http.ResponseEntity;

public class UserToken<T> {
	
	private ResponseEntity<?> token;
    private T user;
    
    

	public UserToken() {
		super();
		// TODO Auto-generated constructor stub
	}



	public UserToken(ResponseEntity<?> token, T user) {
		super();
		this.token = token;
		this.user = user;
	}



	public ResponseEntity<?> getToken() {
		return token;
	}



	public void setToken(ResponseEntity<?> jwtToken) {
		this.token = jwtToken;
	}



	public T getUser() {
		return user;
	}



	public void setUser(T user) {
		this.user = user;
	}



	public void setUser(UserDTO authenticationRequest) {
		this.user = (T) authenticationRequest;
		
	}
	
	public void setData(DAOUser result) {
		this.user=(T) result;
	}



	public void setUser(DAOUser result) {
		this.user=(T) result;
		
	}

	
    
    


}
