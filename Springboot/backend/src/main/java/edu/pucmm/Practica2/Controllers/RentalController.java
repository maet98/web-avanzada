package edu.pucmm.Practica2.Controllers;

import com.google.gson.Gson;
import edu.pucmm.Practica2.DTO.NotReturnedDTO;
import edu.pucmm.Practica2.DTO.RentalDTO;
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

    @GetMapping("/rented")
    public List<NotReturnedDTO> notReturned() {
        return this.rentalService.getNonReturned();
    }

    @PostMapping("return")
    public String ReturnRental(@RequestBody Rental returnDTO) {
        return rentalService.returnRental(returnDTO);
    }

    @GetMapping
    public List<Rental> getAllRental(){
        return this.rentalService.getAll();
    }

    @GetMapping("active")
    public List<Rental> getAllActiveRental() {
        return this.rentalService.getAllActive();
    }

    @GetMapping("client/{id}")
    public List<Rental> getRentalsByClientId(@PathVariable("id")String id){
        return this.rentalService.findByClient(id);
    }


    @GetMapping("/{id}")
    public Rental getById(@PathVariable("id") Long id){
        System.out.println(id);
        var answer= this.rentalService.getById(id);
        return answer;
    }
}
