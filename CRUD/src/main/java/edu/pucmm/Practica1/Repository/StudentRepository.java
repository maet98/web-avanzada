package edu.pucmm.Practica1.Repository;

import edu.pucmm.Practica1.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
