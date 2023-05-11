package ru.msstandart.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.msstandart.dto.*;
import ru.msstandart.entities.*;

@Mapper
public interface EntityDtoMapper {

    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    Order toEntity(OrderDtoIn orderDtoIn);

    @Mapping(source = "status.nameStatus", target = "status")
    OrderDtoOut toDtoOut(Order order);

    @Mapping(source = "status.nameStatus", target = "status")
    OrderDtoForList toDtoForList(Order order);

    UserDto toDto(User user);

    ProfileDto profileToDto(User user);

    ImageDto toDto(Image image);

    PriceDto toDto(Options options);

    PostDto toDto(Post post);

    Post toEntity(PostDto postDto);


}
