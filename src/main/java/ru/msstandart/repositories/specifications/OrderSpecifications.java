package ru.msstandart.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.msstandart.entities.Order;
import ru.msstandart.enumeration.OrderLocations;
import ru.msstandart.enumeration.StatusOrder;

public class OrderSpecifications {

    public static Specification<Order> getOrdersInLocation(String location) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("orderLocations"), OrderLocations.valueOf(location));
    }

    public static Specification<Order> getOrdersByStatus(String status) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), StatusOrder.valueOf(status));
    }
}
