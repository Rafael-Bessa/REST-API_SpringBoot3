package bessa.morangon.rafael.clinica.configuration.security;

import bessa.morangon.rafael.clinica.domain.model.Admin;
import bessa.morangon.rafael.clinica.domain.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicoAutenticacao implements UserDetailsService {
    @Autowired
    private AdminRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Este admin não existe"));
    }
}
