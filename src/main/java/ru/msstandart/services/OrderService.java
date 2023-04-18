package ru.msstandart.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.msstandart.dto.OrderDtoIn;
import ru.msstandart.dto.OrderDtoForList;
import ru.msstandart.dto.OrderDtoOut;
import ru.msstandart.entities.Order;
import ru.msstandart.enumeration.OrderLocations;
import ru.msstandart.enumeration.StatusOrder;
import ru.msstandart.exceptions.ResourceNotFoundException;
import ru.msstandart.mappers.EntityDtoMapper;
import ru.msstandart.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

   private final OrderRepository orderRepository;
   private final UserService userService;

   public Page<OrderDtoForList> getAll(Integer page) {
      return orderRepository.findByOrderByCreatedAtDesc(PageRequest.of(page - 1, 10)).map(EntityDtoMapper.INSTANCE::toDtoForList);
   }

   public OrderDtoOut findOrderById(Long id) {
      Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Order '%d' not found", id)));
      return EntityDtoMapper.INSTANCE.toDtoOut(order);
   }

   @Transactional
   public void saveOrder(String username, OrderDtoIn orderDtoIn) {
      Order order = EntityDtoMapper.INSTANCE.toEntity(orderDtoIn);
      if (orderDtoIn.getNotStandardFigure() != null) order.setStoneFigure(orderDtoIn.getNotStandardFigure());
      if (orderDtoIn.getNotStandardSize() != null) order.setStoneSize(orderDtoIn.getNotStandardSize());
      if (orderDtoIn.getDateOnMonument1() != null && orderDtoIn.getDateOnMonument2() != null) order.setDateOnMonument(orderDtoIn.getDateOnMonument1() + " - " + orderDtoIn.getDateOnMonument2());
      order.setOrderLocations(OrderLocations.valueOf(userService.findByUsername(username).getLocation()));
      order.setStatus(StatusOrder.Подписание);
      orderRepository.save(order);
   }
}
