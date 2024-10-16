package fr.quickstart.shopping_cart.request;

import fr.quickstart.shopping_cart.model.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data //pour avoir les getters et les setters mais pas comme avec les @Getter ou @Setter
public class AddProductRequest {
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
}
