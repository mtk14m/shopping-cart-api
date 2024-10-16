package fr.quickstart.shopping_cart.service.image;

import fr.quickstart.shopping_cart.dto.ImageDto;
import fr.quickstart.shopping_cart.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id );
    void deleteImageById(Long id);
    List<ImageDto> saveImage(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile image, Long imageId);
}
