package ru.msstandart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.msstandart.enumeration.StatusOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    private String notStandardFigure;

    private String nameOnMonument;

    private String workPackage;

    private BigDecimal stoneCost;

    private BigDecimal workCost;

    private BigDecimal installationCost;

    private BigDecimal orderCost;

    private StatusOrder status;

    private Long previewImageId;

    private LocalDateTime createdAt;

}
