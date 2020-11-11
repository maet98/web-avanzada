package edu.pucmm.Practica5.Repository;

import edu.pucmm.Practica5.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
    User findByEmail(String email);
}
