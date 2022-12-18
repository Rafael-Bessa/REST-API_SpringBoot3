package bessa.morangon.rafael.clinica.configuration.security;

import bessa.morangon.rafael.clinica.domain.repository.AdminRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final String header = "Authorization";
    @Autowired
    private TokenService service;
    @Autowired
    private AdminRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenJWT = recuperaTokenDoCabecalho(request);

        if(tokenJWT != null) {
            var subject = service.getSubjectDoToken(tokenJWT);
            var admin = repository.findByLogin(subject);
            var autenticacao = new UsernamePasswordAuthenticationToken(admin.get(), null, admin.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperaTokenDoCabecalho(HttpServletRequest request) {
        var cabecalho = request.getHeader("Authorization");

        if(cabecalho != null){
            return cabecalho.replace("Bearer ", "");
        }
        return null;
    }





}
