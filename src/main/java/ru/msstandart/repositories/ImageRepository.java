package ru.msstandart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.msstandart.entities.Image;
import ru.msstandart.entities.Order;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByOrder(Order order);

    List<Image> findAllByOrderId(Long orderId);

}

