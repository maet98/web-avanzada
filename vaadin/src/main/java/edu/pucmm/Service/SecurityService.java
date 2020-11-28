package edu.pucmm.Service;

import edu.pucmm.Repository.GerenteRepository;
import edu.pucmm.Repository.RolRepository;
import edu.pucmm.model.Gerente;
import edu.pucmm.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private RolRepository rolRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void crearUsuarioAdmin() {
        //Rol rolGerente = new Rol("ROLE_GERENTE");
        //rolRepository.save(rolGerente);

        Gerente admin = new Gerente();
        admin.setEmail("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        admin.setName("admin");
        admin.setRol("ROLE_ADMIN");
        gerenteRepository.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Gerente gerente = gerenteRepository.findByEmail(username);

        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(gerente.getRol()));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return new org.springframework.security.core.userdetails.User(gerente.getEmail(), gerente.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
