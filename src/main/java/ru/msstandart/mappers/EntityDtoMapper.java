package ru.msstandart.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.msstandart.dto.OrderDto;
import ru.msstandart.entities.Order;

@Mapper
public interface EntityDtoMapper {

    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);
}
