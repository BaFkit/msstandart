package ru.msstandart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.msstandart.dto.OrderDto;
import ru.msstandart.mappers.EntityDtoMapper;
import ru.msstandart.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

   private final OrderRepository orderRepository;

   public List<OrderDto> getAll() {
      return orderRepository.findAll().stream().map(EntityDtoMapper.INSTANCE::toDto).collect(Collectors.toList());
   }
}
