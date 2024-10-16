package fr.quickstart.shopping_cart.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String productNotNotFound) {
        super(productNotNotFound);
    }
}
