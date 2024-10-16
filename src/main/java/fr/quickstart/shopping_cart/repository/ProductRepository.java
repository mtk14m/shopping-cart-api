package fr.quickstart.shopping_cart.repository;

import fr.quickstart.shopping_cart.model.Product;
import fr.quickstart.shopping_cart.request.AddProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String productName);

    List<Product> findByBrandAndName(String brand, String productName);

    Long countByBrandAndName(String brand, String productName);


}
