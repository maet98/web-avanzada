package edu.pucmm.Practica3.Services;

import edu.pucmm.Practica3.Entities.Rol;
import edu.pucmm.Practica3.Entities.User;
import edu.pucmm.Practica3.Repository.RolRepository;
import edu.pucmm.Practica3.Repository.UserRepository;
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
public class SeguridadServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

   private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void crearUsuarioAdmin(){
        System.out.println("Creación del usuario y rol en la base de datos");
        Rol rolAdmin = new Rol("ROLE_ADMIN");
        Rol rolUser = new Rol("ROLE_USER");
        rolRepository.save(rolAdmin);
        rolRepository.save(rolUser);

        User admin = new User();
        admin.setEmail("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        admin.setRoles(new HashSet<>(Arrays.asList(rolAdmin)));
        userRepository.save(admin);

        User user = new User();
        user.setEmail("user");
        user.setPassword(bCryptPasswordEncoder.encode("1234"));
        user.setRoles(new HashSet<>(Arrays.asList(rolUser)));
        userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Rol role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
