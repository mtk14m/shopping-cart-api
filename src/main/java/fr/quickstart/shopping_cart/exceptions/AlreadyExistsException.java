package fr.quickstart.shopping_cart.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String ressourceAlreadyExists) {
        super(ressourceAlreadyExists);
    }
}
