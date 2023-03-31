package ru.msstandart.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.msstandart.enumeration.StatusOrder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String stoneKit;

    private BigDecimal stoneKitCost;

    private String stoneFigure;

    private String nameOnMonument;

    private BigDecimal nameOnMonumentCost;

    private String dateOnMonument;

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

    @Enumerated(EnumType.STRING)
    private StatusOrder status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;

    private Long previewImageId;

}
