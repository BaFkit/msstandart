package ru.msstandart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.msstandart.dto.OrderDto;
import ru.msstandart.services.OrderService;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public Page<OrderDto> getAllOrders(@RequestParam(name = "p", defaultValue = "1") Integer page) {
        if (page < 1) page = 1;
       return orderService.getAll(page);
    }
}
