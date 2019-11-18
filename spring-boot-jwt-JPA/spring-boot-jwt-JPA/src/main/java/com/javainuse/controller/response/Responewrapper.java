package com.javainuse.controller.response;

import com.javainuse.model.DAOUser;
import com.javainuse.model.User;
import com.javainuse.model.UserToken;

public class Responewrapper<T> {
	
    private String errorMessage;
	private boolean status;
    private T data;
    
    
  
	public Responewrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Responewrapper(String errorMessage, boolean status, T data) {
		super();
		this.errorMessage = errorMessage;
		this.status = status;
		this.data = data;
	}


	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(UserToken<User> tokenRes) {
		this.data = (T) tokenRes;
	}
	
	public void setData(T data) {
		this.data = data;
	}

	public void setData(DAOUser result) {
		this.data=(T) result;
	}
    
    

}
