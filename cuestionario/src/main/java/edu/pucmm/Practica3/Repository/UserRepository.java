package edu.pucmm.Practica3.Repository;

import edu.pucmm.Practica3.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
    User findByEmail(String email);
}
