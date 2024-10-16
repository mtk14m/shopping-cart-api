package fr.quickstart.shopping_cart.exceptions;

public class RessourceNotFoundException extends RuntimeException{
    public RessourceNotFoundException(String ressourceNotNotFound) {
        super(ressourceNotNotFound);
    }
}
