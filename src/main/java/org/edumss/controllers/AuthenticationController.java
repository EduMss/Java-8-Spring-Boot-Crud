package org.edumss.controllers;

import lombok.var;
import org.edumss.domain.user.AuthenticationDTO;
import org.edumss.domain.user.UserModel;
import org.edumss.domain.user.UserRepository;
import org.edumss.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injeção do PasswordEncoder

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getName(), data.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);

        //var token = tokenService.generateToken((UserDetails) auth.getPrincipal());
        // Verifique se o principal é uma instância de UserModel
        if (auth.getPrincipal() instanceof UserDetails) {
            // Faça o cast para UserModel com segurança
            // Gere o token usando o objeto UserDetails
            var token = tokenService.generateToken((UserDetails) auth.getPrincipal());

            // Faça o que quiser com o token...
            return ResponseEntity.ok(token);
        } else {
            // Lidar com o caso em que o principal não é uma instância de UserModel
            throw new RuntimeException("O principal não é uma instância de UserModel");
        }
    }

}
