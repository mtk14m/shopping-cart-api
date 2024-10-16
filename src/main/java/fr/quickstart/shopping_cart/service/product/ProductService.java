package fr.quickstart.shopping_cart.service.product;

import fr.quickstart.shopping_cart.exceptions.ProductNotFoundException;
import fr.quickstart.shopping_cart.model.Category;
import fr.quickstart.shopping_cart.model.Product;
import fr.quickstart.shopping_cart.repository.CategoryRepository;
import fr.quickstart.shopping_cart.repository.ProductRepository;
import fr.quickstart.shopping_cart.request.AddProductRequest;
import fr.quickstart.shopping_cart.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest addProductRequest) {
        // Vérifie si la catégorie existe, sinon, la crée
        Category category = Optional.ofNullable(categoryRepository.findByName(
                        addProductRequest.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(addProductRequest.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });

        // Ajoute la catégorie au produit
        addProductRequest.setCategory(category);

        // Crée et sauvegarde le produit
        Product product = createProduct(addProductRequest, category);
        return productRepository.save(product);
    }

    private Product createProduct(AddProductRequest addProductRequest, Category category) {
        return new Product(
                addProductRequest.getName(),
                addProductRequest.getBrand(),
                addProductRequest.getPrice(),
                addProductRequest.getInventory(),
                addProductRequest.getDescription(),
                category
        );
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(
                        productRepository::delete,
                        () -> { throw new ProductNotFoundException("Product not found"); }
                );
    }

    @Override
    public Product updateProduct(ProductUpdateRequest productUpdateRequest, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, productUpdateRequest))
                .map(productRepository::save)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest productUpdateRequest) {
        existingProduct.setName(productUpdateRequest.getName());
        existingProduct.setBrand(productUpdateRequest.getBrand());
        existingProduct.setPrice(productUpdateRequest.getPrice());
        existingProduct.setInventory(productUpdateRequest.getInventory());
        existingProduct.setDescription(productUpdateRequest.getDescription());

        Category category = categoryRepository.findByName(productUpdateRequest.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByBrandAndCategory(String brand, String category) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String productName) {
        return productRepository.findByBrandAndName(brand, productName);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String productName) {
        return productRepository.countByBrandAndName(brand, productName);
    }
}
