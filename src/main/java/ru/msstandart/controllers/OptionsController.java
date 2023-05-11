package ru.msstandart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.msstandart.dto.OrderDtoIn;
import ru.msstandart.dto.PriceDto;
import ru.msstandart.services.OptionsService;

@RestController
@RequestMapping("/api/v1/options")
@RequiredArgsConstructor
public class OptionsController {

    private final OptionsService optionsService;

    @GetMapping("/prices")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public PriceDto getPrice() {
       return optionsService.getPrice();
    }

    @PutMapping("/prices")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void changePrices(@RequestBody PriceDto priceDto) {
        optionsService.changePrices(priceDto);
    }

}
