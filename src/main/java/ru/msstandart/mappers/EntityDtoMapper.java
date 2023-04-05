package ru.msstandart.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.msstandart.dto.OrderDtoIn;
import ru.msstandart.dto.OrderDtoForList;
import ru.msstandart.dto.OrderDtoOut;
import ru.msstandart.entities.Order;

@Mapper
public interface EntityDtoMapper {

    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    Order toEntity(OrderDtoIn orderDtoIn);

    OrderDtoOut toDtoOut(Order order);

    OrderDtoForList toDtoForList(Order order);
}
