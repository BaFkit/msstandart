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

    private String address;

    private String email;

    private int idStone;

    private String stoneMaterial;

    private String stoneSize;

    private String stoneKit;

    private String stoneFigure;

    private String nameOnMonument;

    private String workPackage;

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
