package ru.msstandart.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.msstandart.entities.Order;
import ru.msstandart.enumeration.OrderLocations;
import ru.msstandart.enumeration.StatusOrder;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

//    Page<Order> findByOrderByCreatedAtDesc(Pageable page);

    @Query(value = "select o from Order o where o.orderLocations=:orderLocations order by o.createdAt desc")
    Page<Order> findAllByOrderLocations(Pageable page, OrderLocations orderLocations);

    @Query(value = "select o from Order o where o.status=:statusOrderNew or o.status=:statusOrderInWork or o.status=:statusOrderReady or o.status=:statusOrderClarification order by o.createdAt desc")
    Page<Order> findAllWhereStatusIs(Pageable page, StatusOrder statusOrderNew, StatusOrder statusOrderInWork, StatusOrder statusOrderReady, StatusOrder statusOrderClarification);
}
