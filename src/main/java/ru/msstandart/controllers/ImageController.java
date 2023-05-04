package ru.msstandart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.msstandart.entities.Image;
import ru.msstandart.services.ImageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void uploadImageToOrder(@RequestParam("file") MultipartFile file, @PathVariable("orderId") Long orderId, @RequestParam("isPreview") Boolean isPreview) throws IOException {
        imageService.uploadImageToOrder(file, orderId, isPreview);
    }

    @GetMapping("/{orderId}/preview")
    public Image getPreviewImageFromOrder(@PathVariable("orderId") Long orderId) {
       return imageService.getPreviewImageFromOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public List<Image> getImagesFromOrder(@PathVariable("orderId") Long orderId) {
        return imageService.getImagesFromOrder(orderId);
    }
}
