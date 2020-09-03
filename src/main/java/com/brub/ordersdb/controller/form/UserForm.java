package com.brub.ordersdb.controller.form;

import com.brub.ordersdb.model.User;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.UniqueConstraint;

public class UserForm {

    private String name;

    private String email;
    private String senha;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public User convert() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String hash = encoder.encode(senha);
        return new User(email, hash, name);
    }
}
