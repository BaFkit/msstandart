package ru.msstandart.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
import ru.msstandart.repositories.specifications.OrderSpecifications;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

   private final OrderRepository orderRepository;
   private final UserService userService;
   private final RoleService roleService;

   @Transactional
   public Page<OrderDtoForList> getAll(String username, String location, String status, Integer page) {
      User user = userService.findByUsername(username);

      if (user.getRoles().contains(roleService.findRoleByName("ROLE_ADMIN"))) {
         Specification<Order> spec = Specification.where(null);
         if (location != null) spec = spec.and(OrderSpecifications.getOrdersInLocation(location));
         if (status != null) spec = spec.and(OrderSpecifications.getOrdersByStatus(status));
         return orderRepository.findAll(spec, PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"))).map(EntityDtoMapper.INSTANCE::toDtoForList);
      }
      if (user.getRoles().contains(roleService.findRoleByName("ROLE_MAKER"))) {
         return orderRepository.findAllWhereStatusIs(PageRequest.of(page - 1, 10), StatusOrder.New, StatusOrder.In_work, StatusOrder.Ready, StatusOrder.Clarification).map(EntityDtoMapper.INSTANCE::toDtoForList);
      }
      return orderRepository.findAllByOrderLocations(PageRequest.of(page - 1, 10), OrderLocations.valueOf(user.getLocation())).map(EntityDtoMapper.INSTANCE::toDtoForList);
   }

   public OrderDtoOut getOrderById(Long id) {
      Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Order '%d' not found", id)));
      return EntityDtoMapper.INSTANCE.toDtoOut(order);
   }

   public Order findOrderById(Long id) {
      return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Order '%d' not found", id)));
   }

   @Transactional
   public void saveOrder(String username, OrderDtoIn orderDtoIn) {
      Order order = EntityDtoMapper.INSTANCE.toEntity(orderDtoIn);
      if (orderDtoIn.getNotStandardFigure() != null) order.setStoneFigure(orderDtoIn.getNotStandardFigure());
      if (orderDtoIn.getNotStandardSize() != null) order.setStoneSize(orderDtoIn.getNotStandardSize());
      if (orderDtoIn.getNotStandardKit() != null) order.setStoneKit(orderDtoIn.getNotStandardKit());
      if (orderDtoIn.getDateOnMonument1() != null && orderDtoIn.getDateOnMonument2() != null) order.setDateOnMonument(orderDtoIn.getDateOnMonument1() + " - " + orderDtoIn.getDateOnMonument2());
      order.setOrderLocations(OrderLocations.valueOf(userService.findByUsername(username).getLocation()));
      order.setStatus(StatusOrder.Signing);
      orderRepository.save(order);
   }

   @Transactional
   public void changeOrderStatus(Long orderId, String status) {
      Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(String.format("Order '%d' not found", orderId)));
      order.setStatus(StatusOrder.valueOf(status));
   }
}
