package dao;

import dtos.HealthProductDTO;
import persistence.model.Product;

import java.util.List;
import java.util.Set;

public interface iDAO<T, D> {

    boolean initiateProducts();

    D getById(int id);

    Set<D> getByCategory(String category);

    D create(T product);

    D update(D healthProductDTO);

    D delete(int id);

    Set<D> getTwoWeeksToExpire();

    List<D> productsLessThan50Calories();

    D convertToDTO(T product);

    T convertToEntity(D healthProductDTO);
}
