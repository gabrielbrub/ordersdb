package com.brub.ordersdb.controller.form;

import com.brub.ordersdb.model.Product;
import com.brub.ordersdb.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginForm {

	private String email;
	private String senha;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

//	public User convert() {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//		String hash = encoder.encode(senha);
//		return new User(email, hash);
//	}

}
