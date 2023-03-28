package ru.msstandart.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.msstandart.dto.OrderDto;
import ru.msstandart.entities.Order;
import ru.msstandart.enumeration.StatusOrder;
import ru.msstandart.exceptions.ResourceNotFoundException;
import ru.msstandart.mappers.EntityDtoMapper;
import ru.msstandart.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

   private final OrderRepository orderRepository;

   public Page<OrderDto> getAll(Integer page) {
      return orderRepository.findByOrderByCreatedAtDesc(PageRequest.of(page - 1, 10)).map(EntityDtoMapper.INSTANCE::toDto);
   }

   public OrderDto findOrderById(Long id) {
      Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Order '%d' not found", id)));
      return EntityDtoMapper.INSTANCE.toDto(order);
   }

   @Transactional
   public void saveOrder(OrderDto orderDto) {
      Order order = EntityDtoMapper.INSTANCE.toEntity(orderDto);
      if (orderDto.getNotStandardFigure() != null) order.setStoneFigure(orderDto.getNotStandardFigure());
      order.setStatus(StatusOrder.Подписание);
      orderRepository.save(order);
   }

//   private Image toImageEntity(MultipartFile file) throws IOException {
//      Image image = new Image();
//      image.setName(file.getName());
//      image.setOriginalFileName(image.getOriginalFileName());
//      image.setSize(file.getSize());
//      image.setBytes(file.getBytes());
//      return image;
//   }
//
//   public void saveOrder(OrderDto orderDto, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4) throws IOException {
//      Order order = EntityDtoMapper.INSTANCE.toEntity(orderDto);
//      Image image1;
//      Image image2;
//      Image image3;
//      Image image4;
//      if (file1.getSize() != 0) {
//         image1 = toImageEntity(file1);
//         image1.setPreviewImage(true);
//         image1.setOrder(order);
//         order.getImages().add(image1);
//      }
//      if (file2.getSize() != 0) {
//         image2 = toImageEntity(file2);
//         image2.setOrder(order);
//         order.getImages().add(image2);
//      }
//      if (file3.getSize() != 0) {
//         image3 = toImageEntity(file3);
//         image3.setOrder(order);
//         order.getImages().add(image3);
//      }
//      if (file4.getSize() != 0) {
//         image4 = toImageEntity(file4);
//         image4.setOrder(order);
//         order.getImages().add(image4);
//      }
//      Order orderFromDb = orderRepository.save(order);
//      orderFromDb.setPreviewImageId(orderFromDb.getImages().get(0).getId());
//      orderRepository.save(order);
//   }
}
