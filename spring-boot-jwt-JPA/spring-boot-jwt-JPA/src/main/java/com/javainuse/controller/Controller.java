package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.controller.response.Responewrapper;
import com.javainuse.model.DAOUser;
import com.javainuse.model.User;
import com.javainuse.model.UserDTO;
import com.javainuse.service.JwtUserDetailsService;

@RequestMapping({"/api/v1"})

@RestController
public class Controller {
	
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping({ "/hello" })
	public Responewrapper<String> firstPage() {
		String res= "Hello World";
		Responewrapper<String> wrapper= new Responewrapper<>();
		wrapper.setErrorMessage(null);
		wrapper.setStatus(true);
		wrapper.setData(res);
		
		return wrapper;
	}
	
	@PostMapping({"/user/login"})
	public Responewrapper<User> logIn(@RequestBody User user){
		
		Responewrapper<User> wrapper= new Responewrapper<>();
		
		userDetailsService.get();;

		wrapper.setErrorMessage(null);
		wrapper.setStatus(true);
		//wrapper.setData();
		
		return wrapper;
		
		
	}

}