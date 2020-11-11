package edu.pucmm.Practica2.services;

import edu.pucmm.Practica2.entities.MyOrder;
import edu.pucmm.Practica2.repositories.OrderRepository;
import edu.pucmm.Practica2.utils.ListMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public HashMap<Long, MyOrder> getOrder() {
        var Orders = orderRepository.findAll();
        HashMap<Long, MyOrder> map = new HashMap<>();
        for(var actual: Orders) {
            map.put(actual.getId(),actual);
        }
        return map;
    }

    public List<MyOrder> returnOrders(List<MyOrder> orders){
        var ordersAvailable = getOrder();
        List<MyOrder> actualizar = new ArrayList<>();
        for(MyOrder order: orders){
            if(ordersAvailable.containsKey(order.getId())){
                var actual = ordersAvailable.get(order.getId());
                actual.setQuantity(actual.getQuantity() - order.getQuantity());
                actualizar.add(actual);
            }
        }
        return (List<MyOrder>) ListMaker.makeCollection(orderRepository.saveAll(actualizar));
    }
}
