package fr.quickstart.shopping_cart.service.product;

import fr.quickstart.shopping_cart.model.Product;
import fr.quickstart.shopping_cart.request.AddProductRequest;
import fr.quickstart.shopping_cart.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest addProductRequest);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
    Product updateProduct(ProductUpdateRequest productUpdateRequest, Long productId);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByBrandAndCategory(String brand, String category);
    List<Product> getProductByName(String productName);
    List<Product> getProductByBrandAndName(String brand, String productName);
    Long countProductsByBrandAndName(String brand, String productName);
}
