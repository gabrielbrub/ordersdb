package com.brub.ordersdb.controller;


import com.brub.ordersdb.config.TokenService;
import com.brub.ordersdb.controller.dto.TokenDto;
import com.brub.ordersdb.controller.form.LoginForm;
import com.brub.ordersdb.controller.form.UserForm;
import com.brub.ordersdb.model.User;
import com.brub.ordersdb.repository.UserRepository;
import io.swagger.annotations.Api;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
		User user = form.convert();
		try {
			userRepository.save(user);
		}catch (ConstraintViolationException e){
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Email already in use", HttpStatus.CONFLICT);
		}
		return ResponseEntity.ok().build();
	}

	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
