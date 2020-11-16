package edu.pucmm.Practica2.Controllers;

import edu.pucmm.Practica2.DTO.EquimentForm;
import edu.pucmm.Practica2.DTO.UsedEquiment;
import edu.pucmm.Practica2.entities.Equiment;
import edu.pucmm.Practica2.services.EquimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return this.equimentService.createEquiment(equimentForm);
    }

    @GetMapping("/used")
    public List<UsedEquiment> getRentedEquiment(){
        return this.equimentService.getNotReceivedEquiment();
    }

    @PutMapping("/{id}")
    public Equiment updateEquiment(@PathVariable Long id, @ModelAttribute EquimentForm equiment) throws IOException {
        return this.equimentService.updateEquiment(id,equiment);
    }
}

