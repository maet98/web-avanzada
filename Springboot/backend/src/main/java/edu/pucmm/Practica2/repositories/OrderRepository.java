package edu.pucmm.Practica2.repositories;


import edu.pucmm.Practica2.entities.MyOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<MyOrder, Long> {
}
