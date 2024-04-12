package persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDate updatedTimeStamp;
    private int totalAmount;
    private int shelfNumber;
    @OneToMany
    Set<Product> products;

    public void addProduct(Product product){
        if(product != null){
            products.add(product);
        }
    }
    public void removeProduct(Product product){
        if(product != null){
            products.remove(product);
        }
    }
}