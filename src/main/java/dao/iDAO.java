package dao;

import dtos.HealthProductDTO;
import persistence.model.Product;

import java.util.List;
import java.util.Set;

public interface iDAO {

    boolean initiateProducts();
    HealthProductDTO getById(int id);

    Set<HealthProductDTO> getByCategory(String category);

    HealthProductDTO create(Product product);

    HealthProductDTO update(HealthProductDTO healthProductDTO);

    HealthProductDTO delete(int id);

    Set<HealthProductDTO> getTwoWeeksToExpire();

    List<HealthProductDTO> productsLessThan50Calories();

    HealthProductDTO convertToDTO(Product product);

    Product convertToEntity(HealthProductDTO healthProductDTO);
}
