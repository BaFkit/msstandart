package ru.msstandart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.msstandart.converters.OrderConverter;
import ru.msstandart.dto.OrderDto;
import ru.msstandart.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @GetMapping
    public List<OrderDto> getAllOrders() {
       return orderService.getAll().stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
