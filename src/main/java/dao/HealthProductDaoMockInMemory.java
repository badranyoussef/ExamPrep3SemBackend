package dao;

import dtos.HealthProductDTO;
import exceptions.DatabaseException;
import lombok.Getter;
import persistence.model.Product;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

// 1.4.3
@Getter
public class HealthProductDaoMockInMemory implements iDAO<HealthProductDTO, Product>{

    private String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    private Map<Integer, Product> products = new HashMap<>();
    private int counter = 0;
    private LocalDate expireDate = LocalDate.now().plusYears(2);

    @Override
    public Set<HealthProductDTO> getAll() {
        return products.values().stream()  // Opretter en stream fra værdierne i mappen
                .map(this::convertToDTO)  // Konverter hvert Product til en HealthProductDTO
                .collect(Collectors.toSet());  // Samler dem i et Set
    }

    public boolean initiateProducts() {

        Product p1 = new Product("Mineral", "Magnesium", 63.36, 229, "Magnesium for bedre sundhed og velvære.", LocalDate.of(2024, 6, 20));
        Product p2 = new Product("Vitamin", "Multivitamin", 25.97, 101, "Multivitamin for bedre sundhed og velvære.", LocalDate.of(2025, 4, 5));
        Product p3 = new Product("Fiber", "Psyllium Husk", 64.59, 91, "Psyllium Husk for bedre sundhed og velvære.", LocalDate.of(2025, 2, 21));
        Product p4 = new Product("Mineral", "Zink", 74.9, 329, "Zink for bedre sundhed og velvære.", LocalDate.of(2024, 10, 3));
        Product p5 = new Product("Mineral", "Magnesium", 8.36, 65, "Magnesium for bedre sundhed og velvære.", LocalDate.of(2025, 3, 16));
        Product p6 = new Product("Vitamin", "Vitamin C", 64.08, 356, "Vitamin C for bedre sundhed og velvære.", LocalDate.of(2025, 1, 10));
        Product p7 = new Product("Omega-3", "Fiskeolie", 30.68, 0, "Fiskeolie for bedre sundhed og velvære.", LocalDate.of(2025, 1, 20));
        Product p8 = new Product("Vitamin", "Vitamin C", 34.0, 456, "Vitamin C for bedre sundhed og velvære.", LocalDate.of(2025, 1, 6));
        Product p9 = new Product("Fiber", "Psyllium Husk", 17.83, 263, "Psyllium Husk for bedre sundhed og velvære.", LocalDate.of(2024, 10, 15));

        create(p1);
        create(p2);
        create(p3);
        create(p4);
        create(p5);
        create(p6);
        create(p7);
        create(p8);
        create(p9);

        if (!products.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public HealthProductDTO getById(int id) {
        Product product = products.get(id);
        if (product == null) {
            throw new DatabaseException(400, "No products found with ID: " + id, timeStamp);
        }
        return convertToDTO(product);
    }


    public Set<HealthProductDTO> getByCategory(String category) {
        Set<HealthProductDTO> filteredProducts = new HashSet<>();
        boolean found = false;

        for (Product product : products.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(convertToDTO(product));
                found = true;
            }
        }
        if (!found) {
            throw new DatabaseException(400, "No products found with category: " + category, timeStamp);
//            400 Bad Request: Hvis der er noget galt med klientens anmodning, såsom ugyldige forespørgselsparametre.
        }
        return filteredProducts;
    }

    @Override
    public HealthProductDTO create(Product product) {
        counter++;
        try{
            product.setId(counter);
            product.setExpireDate(expireDate);
            products.put(counter, product);
        }catch(DatabaseException e){
            throw new DatabaseException(e.getStatusCode(),"Product could not be added to database. Product is null",e.getTimeStamp());
        }
        return convertToDTO(product);
    }
    @Override
    public HealthProductDTO update(HealthProductDTO healthProductDTO) {

        Product product = products.get(healthProductDTO.getId());
        if (product == null) {
            throw new DatabaseException(400, "Product not found with ID: " + healthProductDTO.getId(), timeStamp);
        }
        try{
            product = convertToEntity(healthProductDTO);
            products.put(product.getId(), product);
        }catch(DatabaseException e){
            throw new DatabaseException(e.getStatusCode(),"Unable to update product",e.getTimeStamp());
        }
        return convertToDTO(product);
    }
    @Override
    public HealthProductDTO delete(int id) {
        if (products.containsKey(id)) {
            Product removedProduct = products.remove(id); // .remove() returnerer det fjernede objekt, hvis det findes
            return convertToDTO(removedProduct);
        } else {
            throw new DatabaseException(400, "Product not found with ID: " + id, timeStamp);
        }
    }

    public Set<HealthProductDTO> getTwoWeeksToExpire() {

//      Der er som sådan ikke behov for exception her, da det er ok at returnere et tomt Set
//      hvis der ikke finde produkter som udløber indenfor 2 uger

        Set<HealthProductDTO> toExpireSoon = new HashSet<>();
        LocalDate twoWeeksFromNow = LocalDate.now().plusWeeks(2);

        for (Product product : products.values()) {
            if (product.getExpireDate().isBefore(twoWeeksFromNow) || product.getExpireDate().isEqual(twoWeeksFromNow)) {
                toExpireSoon.add(convertToDTO(product));
            }
        }
        return toExpireSoon;
    }

    public List<HealthProductDTO> productsLessThan50Calories(){

        List<HealthProductDTO> list = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.getCalories()>50) {
                list.add(convertToDTO(product));
            }
        }
        return list;
    }


    public HealthProductDTO convertToDTO(Product product) {
        return HealthProductDTO.builder()
                .id(product.getId())
                .category(product.getCategory())
                .name(product.getName())
                .calories(product.getCalories())
                .price(product.getPrice())
                .description(product.getDescription())
                .expireDate(product.getExpireDate())
                .build();
    }

    public Product convertToEntity(HealthProductDTO healthProductDTO) {
        return Product.builder()
                .id(healthProductDTO.getId())
                .category(healthProductDTO.getCategory())
                .name(healthProductDTO.getName())
                .calories(healthProductDTO.getCalories())
                .price(healthProductDTO.getPrice())
                .description(healthProductDTO.getDescription())
                .expireDate(healthProductDTO.getExpireDate())
                .build();
    }
}
