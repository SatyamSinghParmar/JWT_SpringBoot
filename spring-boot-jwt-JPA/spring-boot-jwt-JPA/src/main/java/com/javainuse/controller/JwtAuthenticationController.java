package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.config.JwtTokenUtil;
import com.javainuse.controller.response.Responewrapper;
import com.javainuse.model.DAOUser;
import com.javainuse.model.JwtRequest;
import com.javainuse.model.JwtResponse;
import com.javainuse.model.User;
import com.javainuse.model.UserDTO;
import com.javainuse.model.UserToken;
import com.javainuse.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	/*@RequestMapping(value = "/usertoken", method = RequestMethod.POST)
	public Responewrapper<UserToken> createAuthenticationToken1(@RequestBody UserDTO authenticationRequest) throws Exception {
		Responewrapper<UserToken> wrapper= new Responewrapper<>();
		DAOUser result=userDetailsService.save(authenticationRequest);
				
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		JwtResponse tokenResponse=new JwtResponse(token);
		
		UserToken<User> tokenRes= new UserToken<>();
		tokenRes.setToken(tokenResponse);
		tokenRes.setUser(result);
	
		wrapper.setErrorMessage(null);
		wrapper.setStatus(true);
		wrapper.setData(tokenRes);
		
		return wrapper;
		
		
	}*/
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Responewrapper<User> saveUser(@RequestBody UserDTO user) throws Exception {
		
		Responewrapper<User> wrapper= new Responewrapper<>();
		DAOUser result=userDetailsService.save(user);
		wrapper.setErrorMessage(null);
		wrapper.setStatus(true);
		wrapper.setData(result);		
		return wrapper;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}