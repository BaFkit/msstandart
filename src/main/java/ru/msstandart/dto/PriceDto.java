package ru.msstandart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {

    private String name;

    private Integer letterNameCost;

    private Integer letterEpitaphCost;

    private Integer digitCost;

}
