package edu.pucmm.Service;

import edu.pucmm.Repository.GerenteRepository;
import edu.pucmm.model.Gerente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Stream;

@Service
public class GerenteService {

    @Autowired
    GerenteRepository gerenteRepository;

    public void crearGerentes(Gerente gerente){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        gerente.setPassword(encoder.encode(gerente.getPassword()));
        gerenteRepository.save(gerente);
    }

    public Gerente buscarPorId(Long id) {
        return this.gerenteRepository.findById(id).get();
    }

    public Gerente modificar(Long id, Gerente gerente) {
        Gerente old = this.gerenteRepository.findById(id).get();
        if(old != null) {
            old.setName(gerente.getName());
            old.setEmail(gerente.getEmail());
            return this.gerenteRepository.save(old);
        }
        return null;
    }

    public Gerente buscarPorEmail(String email) { return gerenteRepository.findByEmail(email);}

    public List<Gerente> listaGerentes(){
        return gerenteRepository.findAll();
    }

    public Stream<Gerente> listaGerente(Pageable pageable){
        return gerenteRepository.findAll(pageable).stream();
    }

    public long cantidadGerente(){
        return gerenteRepository.count();
    }

    public void borrarGerente(Gerente gerente) {
        gerenteRepository.delete(gerente);
    }

    public String singin(String email, String paasword) {
        return "";
    }

}
