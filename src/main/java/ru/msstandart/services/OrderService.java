package ru.msstandart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.msstandart.entities.Order;
import ru.msstandart.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

   private final OrderRepository orderRepository;

   public List<Order> getAll() {
      return orderRepository.findAll();
   }
}
