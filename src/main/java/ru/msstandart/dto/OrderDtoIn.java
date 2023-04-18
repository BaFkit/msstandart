package ru.msstandart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.msstandart.enumeration.StatusOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDtoIn {

    private Long id;
    private String fullName;
    private String phone;
    private String phoneSecond;
    private String address;
    private String email;
    private Integer idStone;
    private String vendorCode;
    private BigDecimal monumentCost;
    private String stoneMaterial;
    private String stonePolishing;
    private String stoneSize;
    private String notStandardSize;
    private String stoneKit;
    private BigDecimal stoneKitCost;
    private String stoneFigure;
    private String notStandardFigure;
    private String nameOnMonument;
    private BigDecimal nameOnMonumentCost;
    private LocalDate dateOnMonument1;
    private LocalDate dateOnMonument2;
    private BigDecimal dateOnMonumentCost;
    private Integer holesInStand;
    private BigDecimal holesInStandCost;
    private String portrait;
    private BigDecimal portraitCost;
    private BigDecimal portraitFasteningCost;
    private String tile;
    private BigDecimal tileCost;
    private BigDecimal tileFasteningCost;
    private String crucifix;
    private BigDecimal crucifixCost;
    private String flowers;
    private BigDecimal flowersCost;
    private String pictureOne;
    private BigDecimal pictureOneCost;
    private String pictureTwo;
    private BigDecimal pictureTwoCost;
    private String pictureThree;
    private BigDecimal pictureThreeCost;
    private String frame;
    private BigDecimal frameCost;
    private String epitaph;
    private BigDecimal epitaphCost;
    private String otherWorksOnMonument;
    private BigDecimal otherWorksOnMonumentCost;
    private String installationLocation;
    private BigDecimal monumentInstallationCost;
    private String otherInstallation;
    private BigDecimal otherInstallationCost;
    private String otherInfo;
    private BigDecimal stoneCost;
    private BigDecimal workCost;
    private BigDecimal installationCost;
    private BigDecimal orderCost;
    private StatusOrder status;
    private Long previewImageId;
    private LocalDateTime createdAt;

}
