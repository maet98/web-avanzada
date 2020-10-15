package edu.pucmm.Practica2.Controllers;

import edu.pucmm.Practica2.DTO.ClientForm;
import edu.pucmm.Practica2.entities.Client;
import edu.pucmm.Practica2.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        System.out.println("mierda");
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    public Client getById(Long id) {
        return this.clientService.getById(id);
    }

    @PostMapping
    public Client createClient(@ModelAttribute ClientForm model) throws IOException {
        System.out.println(model.getCedula());
        return this.clientService.createClient(model);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable("id") String id){
        return null;
    }

    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        this.clientService.deleteClient(id);
    }
}
