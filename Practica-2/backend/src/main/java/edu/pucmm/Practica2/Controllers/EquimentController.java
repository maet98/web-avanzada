package edu.pucmm.Practica2.Controllers;

import edu.pucmm.Practica2.Application;
import edu.pucmm.Practica2.DTO.EquimentForm;
import edu.pucmm.Practica2.entities.Equiment;
import edu.pucmm.Practica2.services.EquimentService;
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

    @PostMapping
    public Equiment createEquiement(@ModelAttribute EquimentForm model) throws IOException {
        Equiment new_equiment = new Equiment(model.getName(),
                model.getQuantity(),
                model.getCost());
        return equimentService.createEquiment(new_equiment,model.getImage());
    }

}
