package dao;

import dtos.HealthProductDTO;
import jakarta.persistence.EntityManagerFactory;
import persistence.config.HibernateConfig;
import persistence.model.Product;

import java.util.List;
import java.util.Set;

public class HealthProductDAODB implements iDAO{

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

    @Override
    public boolean initiateProducts() {
        return false;
    }

    @Override
    public HealthProductDTO getById(int id) {
        return null;
    }

    @Override
    public Set<HealthProductDTO> getByCategory(String category) {
        return null;
    }

    @Override
    public HealthProductDTO create(Product product) {
        return null;
    }

    @Override
    public HealthProductDTO update(HealthProductDTO healthProductDTO) {
        return null;
    }

    @Override
    public HealthProductDTO delete(int id) {
        return null;
    }

    @Override
    public Set<HealthProductDTO> getTwoWeeksToExpire() {
        return null;
    }

    @Override
    public List<HealthProductDTO> productsLessThan50Calories() {
        return null;
    }

    @Override
    public HealthProductDTO convertToDTO(Product product) {
        return null;
    }

    @Override
    public Product convertToEntity(HealthProductDTO healthProductDTO) {
        return null;
    }
}
