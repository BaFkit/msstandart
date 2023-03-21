package ru.msstandart.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.msstandart.dto.OrderDto;
import ru.msstandart.mappers.EntityDtoMapper;
import ru.msstandart.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

   private final OrderRepository orderRepository;

   public Page<OrderDto> getAll(Integer page) {
      return orderRepository.findAll(PageRequest.of(page - 1, 10)).map(EntityDtoMapper.INSTANCE::toDto);
   }
}
