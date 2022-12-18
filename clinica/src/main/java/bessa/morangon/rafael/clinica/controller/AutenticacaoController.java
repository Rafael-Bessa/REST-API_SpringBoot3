package bessa.morangon.rafael.clinica.controller;

import bessa.morangon.rafael.clinica.configuration.security.TokenDto;
import bessa.morangon.rafael.clinica.configuration.security.TokenService;
import bessa.morangon.rafael.clinica.domain.model.Admin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid Admin admin){

        var autenticacao = new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword());
        Authentication authenticate = manager.authenticate(autenticacao);

        String tokenJWT = tokenService.gerarToken((Admin) authenticate.getPrincipal());

        return ResponseEntity.ok(new TokenDto(tokenJWT));
    }



}
