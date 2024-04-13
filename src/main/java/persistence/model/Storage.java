package persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "storages")
@Builder
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private LocalDate updatedTimeStamp;
    private int totalAmount; //total produkter på hylden
    private int shelfNumber; //hylde nummer

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storage", fetch = FetchType.EAGER)
    Set<Product> products = new HashSet<>();

    public void addProduct(Product product){
        if(product != null){
            products.add(product);
            product.setStorage(this);
        }
    }
    public void removeProduct(Product product){
        if(product != null){
            products.remove(product);
        }
    }

    public Storage(LocalDate updatedTimeStamp, int totalAmount, int shelfNumber) {
        this.updatedTimeStamp = updatedTimeStamp;
        this.totalAmount = totalAmount;
        this.shelfNumber = shelfNumber;

    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", updatedTimeStamp=" + updatedTimeStamp +
                ", totalAmount=" + totalAmount +
                ", shelfNumber=" + shelfNumber +
                ", products=" + products +
                '}';
    }

    @PrePersist
    @PreUpdate
    private void updateTimeStamp(){
        updatedTimeStamp = LocalDate.now();
        totalAmount = products.size();
    }

}