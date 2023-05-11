package ru.msstandart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.msstandart.dto.ImageDto;
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
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public void uploadImageToOrder(@RequestParam("file") MultipartFile file, @PathVariable("orderId") Long orderId, @RequestParam("isPreview") Boolean isPreview) throws IOException {
        imageService.uploadImageToOrder(file, orderId, isPreview);
    }

    @GetMapping("/{orderId}/preview")
    @PreAuthorize("isAuthenticated()")
    public ImageDto getPreviewImageFromOrder(@PathVariable("orderId") Long orderId) {
       return imageService.getPreviewImageFromOrder(orderId);
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("isAuthenticated()")
    public List<ImageDto> getImagesFromOrder(@PathVariable("orderId") Long orderId) {
        return imageService.getImagesFromOrder(orderId);
    }
}
