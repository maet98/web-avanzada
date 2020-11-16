package edu.pucmm.Practica2.Controllers;

import edu.pucmm.Practica2.DTO.LoginDTO;
import edu.pucmm.Practica2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public String Login(@RequestBody LoginDTO payload){
        System.out.println(payload.getEmail());
        return userService.signin(payload.getEmail(),payload.getPassword());
    }
}
