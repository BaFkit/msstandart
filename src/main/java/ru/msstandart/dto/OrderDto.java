package ru.msstandart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private String fullName;

    private String phone;

    private String address;

    private String email;

    private int idStone;

    private String stoneMaterial;

    private String stoneSize;

    private String stoneKit;

    private String stoneFigure;

    private String workPackage;

    private BigDecimal stoneCost;

    private BigDecimal workCost;

    private BigDecimal orderCost;

    private String status;
}
