package ru.msstandart.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.msstandart.dto.OrderDtoIn;
import ru.msstandart.dto.OrderDtoForList;
import ru.msstandart.dto.OrderDtoOut;
import ru.msstandart.dto.UserDto;
import ru.msstandart.entities.Order;
import ru.msstandart.entities.User;

@Mapper
public interface EntityDtoMapper {

    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    Order toEntity(OrderDtoIn orderDtoIn);

    @Mapping(source = "status.nameStatus", target = "status")
    OrderDtoOut toDtoOut(Order order);

    @Mapping(source = "status.nameStatus", target = "status")
    OrderDtoForList toDtoForList(Order order);

    UserDto toDto(User user);
}
