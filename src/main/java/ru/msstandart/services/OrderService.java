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
import ru.msstandart.entities.User;
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
   private final RoleService roleService;

   @Transactional
   public Page<OrderDtoForList> getAll(String username, Integer page) {
      User user = userService.findByUsername(username);
      if (user.getRoles().contains(roleService.findRoleByName("ROLE_ADMIN"))) {
         return orderRepository.findByOrderByCreatedAtDesc(PageRequest.of(page - 1, 10)).map(EntityDtoMapper.INSTANCE::toDtoForList);
      }
      if (user.getRoles().contains(roleService.findRoleByName("ROLE_MAKE"))) {
         return orderRepository.findAllWhereStatusIsNot(PageRequest.of(page - 1, 10), StatusOrder.Новый, StatusOrder.В_Работе, StatusOrder.Готов).map(EntityDtoMapper.INSTANCE::toDtoForList);
      }
      return orderRepository.findAllByOrderLocations(PageRequest.of(page - 1, 10), OrderLocations.valueOf(user.getLocation())).map(EntityDtoMapper.INSTANCE::toDtoForList);

//      return orderRepository.findByOrderByCreatedAtDesc(PageRequest.of(page - 1, 10)).map(EntityDtoMapper.INSTANCE::toDtoForList);
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

   @Transactional
   public void changeOrderStatus(Long orderId, String status) {
      Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(String.format("Order '%d' not found", orderId)));
      order.setStatus(StatusOrder.valueOf(status));
   }
}
