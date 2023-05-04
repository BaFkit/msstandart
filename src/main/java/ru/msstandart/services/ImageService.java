package ru.msstandart.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.msstandart.entities.Image;
import ru.msstandart.entities.Order;
import ru.msstandart.exceptions.ResourceNotFoundException;
import ru.msstandart.repositories.ImageRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

 private final ImageRepository imageRepository;
 private final OrderService orderService;

    @Transactional
    public Image uploadImageToOrder(MultipartFile file, Long orderId, boolean isPreviewImage) throws IOException {
        Order order = orderService.findOrderById(orderId);
        Image image = new Image();
        image.setOrder(order);
        image.setBytes(compressBytes(file.getBytes()));
        image.setOriginalFileName(file.getOriginalFilename());
        image.setName(order.getVendorCode() + file.getName());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image = imageRepository.save(image);
        if (isPreviewImage) {
            image.setPreviewImage(true);
            order.setPreviewImageId(image.getId());
        }
        return image;
    }

    @Transactional(readOnly = true)
    public List<Image> getImagesFromOrder(Long orderId) {
        Order order = orderService.findOrderById(orderId);
        List<Image> images = order.getImages();
        images.forEach(image -> image.setBytes(decompressBytes(image.getBytes())));
        return images;
    }

    @Transactional(readOnly = true)
    public Image getPreviewImageFromOrder(Long orderId) {
      Order order = orderService.findOrderById(orderId);
      Image image = imageRepository.findById(order.getPreviewImageId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Image with id:'%d' not found", order.getPreviewImageId())));
      image.setBytes(decompressBytes(image.getBytes()));
        System.out.println("123");
      return image;
    }

    @Transactional(readOnly = true)
    public Image getPreviewImage(Long orderId) {
        Order order = orderService.findOrderById(orderId);
        if (order.getPreviewImageId() == null) throw new ResourceNotFoundException("Preview Image image is missing");
        return imageRepository.findById(order.getPreviewImageId()).orElseThrow(() -> new ResourceNotFoundException(String.format("PreviewImage for order with id:'%d' not found", orderId)));
    }

    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
//            LOG.error("Cannot compress Bytes");
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
//            LOG.error("Cannot decompress Bytes");
        }
        return outputStream.toByteArray();
    }

}
