package fr.quickstart.shopping_cart.repository;

import fr.quickstart.shopping_cart.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
