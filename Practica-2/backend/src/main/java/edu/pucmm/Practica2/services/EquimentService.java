package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.entities.Equiment;
import edu.pucmm.Practica2.entities.SubFamily;
import edu.pucmm.Practica2.repositories.EquimentRepository;
import edu.pucmm.Practica2.repositories.SubFamilyRepository;
import edu.pucmm.Practica2.utils.ImageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class EquimentService {

    @Autowired
    private EquimentRepository equimentRepository;

    @Autowired
    private SubFamilyRepository subFamilyRepository;

    public Boolean isPossibleRental(List<Equiment> equimentList){
        var equimentsAvailable = equimentRepository.findAll();
        HashMap<Long, Equiment> map = new HashMap<>();
        for(var actual: equimentsAvailable) {
            map.put(actual.getId(),actual);
        }
        for(var actual: equimentList) {
            if(map.containsKey(actual.getId())){
                var available = map.get(actual.getId());
                if(available.getQuantity() < actual.getQuantity()){
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public List<Equiment> getAll() {
        return equimentRepository.findAll();
    }

    public Equiment createEquiment(Equiment equiment, SubFamily id, MultipartFile image) throws IOException {
        equiment.setImage(ImageProcessor.byteToString(image));
        equiment.set
        return equimentRepository.save(equiment);
    }
}
