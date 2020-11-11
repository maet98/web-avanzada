package edu.pucmm.Practica2.Controllers;

import edu.pucmm.Practica2.Application;
import edu.pucmm.Practica2.DTO.EquimentForm;
import edu.pucmm.Practica2.DTO.LoginDTO;
import edu.pucmm.Practica2.entities.Equiment;
import edu.pucmm.Practica2.services.EquimentService;
import edu.pucmm.Practica2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/equiment")
@CrossOrigin
public class EquimentController {

    @Autowired
    private EquimentService equimentService;

    @GetMapping
    public List<Equiment> getAll() {
        return equimentService.getAll();
    }

    @PostMapping(consumes = "multipart/form-data")
    public Equiment createEquiement(@ModelAttribute EquimentForm equimentForm) throws IOException {
        System.out.println(equimentForm.getImage());
        return this.equimentService.createEquiment(equimentForm);
    }
}
