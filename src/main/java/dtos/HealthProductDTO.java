package dtos;

import lombok.*;

import java.time.LocalDate;


//1.3 implement a HealthProductDTO
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HealthProductDTO {

    private int id;
    private String category;
    private String name;
    private double price;
    private int calories;
    private String description;
    private LocalDate expireDate;

}
