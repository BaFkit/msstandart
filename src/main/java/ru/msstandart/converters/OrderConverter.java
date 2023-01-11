package ru.msstandart.converters;

import org.springframework.stereotype.Component;
import ru.msstandart.dto.OrderDto;
import ru.msstandart.entities.Order;

@Component
public class OrderConverter {

    public Order dtoToEntity(OrderDto orderDto) {
       throw new UnsupportedOperationException();
    }

    public OrderDto entityToDto(Order order) {
        OrderDto out = new OrderDto();
        out.setId(order.getId());
        out.setFullName(order.getFullName());
        out.setPhone(order.getPhone());
        out.setAddress(order.getAddress());
        out.setEmail(order.getEmail());
        out.setIdStone(order.getIdStone());
        out.setStoneMaterial(order.getStoneMaterial());
        out.setStoneSize(order.getStoneSize());
        out.setStoneKit(order.getStoneKit());
        out.setStoneFigure(order.getStoneFigure());
        out.setWorkPackage(order.getWorkPackage());
        out.setStoneCost(order.getStoneCost());
        out.setWorkCost(order.getWorkCost());
        out.setOrderCost(order.getOrderCost());
        out.setStatus(order.getStatus());
        return out;
    }
}
