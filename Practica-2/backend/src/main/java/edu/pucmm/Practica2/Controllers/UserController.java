package edu.pucmm.Practica2.Controllers;

import edu.pucmm.Practica2.DTO.LoginDTO;
import edu.pucmm.Practica2.entities.User;
import edu.pucmm.Practica2.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class UserController {

    @Autowired
    UserRepository usuarioRepository;

    @Value("${token_jwt}")
    private String llaveSecreta;

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody LoginDTO payload){
        String token = "";
        System.out.println(payload.getEmail());
        User usuario = usuarioRepository.findByEmail(payload.getEmail());
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        if(usuario==null && !usuario.getPassword().equals(bCryptPasswordEncoder.encode(payload.getPassword()))){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        token = generarToken(usuario);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @RequestMapping("/")
    public String index(){
        return "Hola Mundo con JWT";
    }

    /**
     *
     * @param usuario
     * @return
     */
    private String generarToken(User usuario) {

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(usuario.getEmail())
                .claim("roles",usuario.getRoles())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        llaveSecreta.getBytes()).compact();

        return "Bearer " + token;
    }
}
