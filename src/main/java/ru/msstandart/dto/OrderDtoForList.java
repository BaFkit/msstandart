package ru.msstandart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.msstandart.enumeration.OrderLocations;
import ru.msstandart.enumeration.StatusOrder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDtoForList {

    private Long id;
    private String nameOnMonument;
    private String phone;
    private LocalDateTime createdAt;
    private OrderLocations orderLocations;
    private String status;

}
