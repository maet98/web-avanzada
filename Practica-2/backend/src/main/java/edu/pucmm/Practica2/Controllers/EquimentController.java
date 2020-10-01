package edu.pucmm.Practica2.Controllers;

import edu.pucmm.Practica2.DTO.EquimentForm;
import edu.pucmm.Practica2.DTO.UserForm;
import edu.pucmm.Practica2.entities.Equiment;
import edu.pucmm.Practica2.entities.User;
import edu.pucmm.Practica2.repositories.SubFamilyRepository;
import edu.pucmm.Practica2.services.EquimentService;
import edu.pucmm.Practica2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller()
@RequestMapping("/equiment")
public class EquimentController {

    @Autowired
    private EquimentService equimentService;

    @Autowired
    private SubFamilyRepository subFamilyRepository;

    @GetMapping
    public String ListAll(Model model) {
        model.addAttribute("equiments", equimentService.getAll());
        return "ListEquiment";
    }

    @PostMapping("/register")
    public String createEquiement(@ModelAttribute EquimentForm model) throws IOException {
        Equiment new_equiment = new Equiment(model.getName(),
                model.getQuantity(),
                model.getCost());
        equimentService.createEquiment(new_equiment,model.getImage());
        return "redirect:/equiment";
    }

    @GetMapping("/register")
    public String userForm(Model model) {
        model.addAttribute("subfamilies",subFamilyRepository.findAll());
        return "EquimentForm";
    }
}
