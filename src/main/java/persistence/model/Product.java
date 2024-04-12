package persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String category;
    private String name;
    private double price;
    private int calories;
    private String description;
    private LocalDate expireDate;
    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;

    public Product(String category, String name, double price, int calories, String description, LocalDate expireDate) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.description = description;
        this.expireDate = expireDate;
    }
}