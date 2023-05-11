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
import ru.msstandart.services.OrderService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Page<OrderDtoForList> getAllOrders(Authentication authentication,
                                              @RequestParam(name = "location", required = false) String location,
                                              @RequestParam(name = "status", required = false) String status,
                                              @RequestParam(name = "p", defaultValue = "1") Integer page) {
        if (page < 1) page = 1;
       return orderService.getAll(authentication.getName(), location, status, page);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public void createOrder(Principal principal, @RequestBody OrderDtoIn orderDtoIn){
        orderService.saveOrder(principal.getName(), orderDtoIn);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public OrderDtoOut getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }


    @GetMapping("/status/change/{id}/{status}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ResponseStatus(HttpStatus.OK)
    public void changeOrderStatus(@PathVariable Long id, @PathVariable String status) {
        orderService.changeOrderStatus(id, status);
    }
}
