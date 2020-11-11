package edu.pucmm.Practica2.Controllers;

import com.google.gson.Gson;
import edu.pucmm.Practica2.DTO.RentalDTO;
import edu.pucmm.Practica2.DTO.ReturnDTO;
import edu.pucmm.Practica2.entities.Rental;
import edu.pucmm.Practica2.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rental")
@CrossOrigin
public class RentalController {

    @Autowired
    private RentalService rentalService;

    private final Gson gson;

    public RentalController() {
        gson = new Gson();
    }

    @PostMapping
    public Rental CreateRental(@RequestBody RentalDTO rental ) throws Exception {
        System.out.println(rental);
        return rentalService.createRental(rental);
    }

    @PostMapping("return")
    public String ReturnRental(@RequestBody ReturnDTO returnDTO) {
        return rentalService.returnRental(returnDTO);
    }

    @GetMapping
    public List<Rental> getAllRental(){
        return this.rentalService.getAll();
    }


    @GetMapping("/{id}")
    public Rental getById(@PathVariable("id") Long id){
        System.out.println(id);
        var answer= this.rentalService.getById(id);
        return answer;
    }
}
