package edu.pucmm.Repository;

import edu.pucmm.model.Event;
import edu.pucmm.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    boolean existsGerenteByEmail(String email);
    Gerente findByEmail(String email);
}
