package edu.pucmm.Practica2.Controllers;

import edu.pucmm.Practica2.DTO.ReturnDTO;
import edu.pucmm.Practica2.entities.Rental;
import edu.pucmm.Practica2.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping
    public Rental CreateRental(@RequestBody Rental rental ) throws Exception {
        return rentalService.createRental(rental);
    }

    @PostMapping("return")
    public String ReturnRental(@RequestBody ReturnDTO returnDTO) {
        return rentalService.returnRental(returnDTO);
    }
}
