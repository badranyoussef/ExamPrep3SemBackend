package dtos;

import lombok.*;
import persistence.model.Product;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StorageDTO {
    private int id;
    private LocalDate updatedTimeStamp;
    private int totalAmount;
    private int shelfNumber;
    Set<Product> products;

    @Override
    public String toString() {
        return "StorageDTO{" +
                "id=" + id +
                ", updatedTimeStamp=" + updatedTimeStamp +
                ", totalAmount=" + totalAmount +
                ", shelfNumber=" + shelfNumber +
                '}';
    }
}
