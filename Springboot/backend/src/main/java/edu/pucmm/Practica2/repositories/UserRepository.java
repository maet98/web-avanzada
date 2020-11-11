package edu.pucmm.Practica2.repositories;

import edu.pucmm.Practica2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    boolean existsUserByEmail(String email);
    User findByEmail(String email);
}
