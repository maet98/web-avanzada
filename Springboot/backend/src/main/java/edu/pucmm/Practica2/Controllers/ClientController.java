package edu.pucmm.Practica2.Controllers;

import edu.pucmm.Practica2.DTO.ClientForm;
import edu.pucmm.Practica2.entities.Client;
import edu.pucmm.Practica2.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/client")
@CrossOrigin
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable("id")String id) {
        return this.clientService.getById(id);
    }

    @PostMapping
    public Client createClient(@ModelAttribute ClientForm model) throws IOException {
        System.out.println(model.getCedula());
        return this.clientService.createClient(model);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable("id") String id,@ModelAttribute ClientForm model){
        return this.clientService.update(id,model);
    }

    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable("id") String id) {
        this.clientService.deleteClient(id);
    }
}
