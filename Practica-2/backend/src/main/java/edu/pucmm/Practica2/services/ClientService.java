package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.DTO.ClientForm;
import edu.pucmm.Practica2.entities.Client;
import edu.pucmm.Practica2.repositories.ClientRepository;
import edu.pucmm.Practica2.utils.ImageProcessor;
import edu.pucmm.Practica2.utils.ListMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return (List<Client>) ListMaker.makeCollection(clientRepository.findAll());
    }

    public Client getById(Long id) {
        return this.clientRepository.findById(id).orElse(null);
    }

    public Client createClient(ClientForm client) throws IOException {
        String image = null;
        if(client.getImage() != null) {
           image = ImageProcessor.byteToString(client.getImage());
        }
        Client new_client = new Client(
                client.getCedula(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                image
                );
        return this.clientRepository.save(new_client);
    }

    public Client update(Client client, String id) {
        return  null;
    }

    public void deleteClient(Long id) {
        this.clientRepository.deleteById(id);
    }
}
