package edu.pucmm.Practica2.Controllers;

import edu.pucmm.Practica2.DTO.UserForm;
import edu.pucmm.Practica2.entities.User;
import edu.pucmm.Practica2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller()
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String ListAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "ListUser";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute UserForm model) throws IOException {
        System.out.println(model.getCedula());
        User new_user = new User(model.getCedula(),
                model.getFirstName(),
                model.getLastName(),
                model.getEmail(),
                model.getPassword());
        userService.createUser(new_user,model.getImage());
        return "redirect:/user";
    }

    @GetMapping("/register")
    public String userForm(Model model) {
        return "UserForm";
    }
}
