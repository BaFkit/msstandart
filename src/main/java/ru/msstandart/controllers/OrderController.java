package ru.msstandart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.msstandart.dto.OrderDtoIn;
import ru.msstandart.dto.OrderDtoForList;
import ru.msstandart.dto.OrderDtoOut;
import ru.msstandart.exceptions.ResourceNotFoundException;
import ru.msstandart.services.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Page<OrderDtoForList> getAllOrders(Authentication authentication, @RequestParam(name = "p", defaultValue = "1") Integer page) {
        if (page < 1) page = 1;
       return orderService.getAll(authentication.getName(), page);
    }

    @PostMapping
    public void createOrder(Authentication authentication, @RequestBody OrderDtoIn orderDtoIn){
        orderService.saveOrder(authentication.getName(), orderDtoIn);
    }

    @GetMapping("/{id}")
    public OrderDtoOut getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping("/status/change/{id}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void changeOrderStatus(@PathVariable Long id, @PathVariable String status) {
        orderService.changeOrderStatus(id, status);
    }
}
