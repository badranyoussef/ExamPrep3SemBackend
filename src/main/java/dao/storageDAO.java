package dao;

import dtos.HealthProductDTO;
import exceptions.DatabaseException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import persistence.config.HibernateConfig;
import persistence.model.Product;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class storageDAO implements iDAO<Product, HealthProductDTO> {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

    @Override
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

        try {
            create(p1);
            create(p2);
            create(p3);
            create(p4);
            create(p5);
            create(p6);
            create(p7);
            create(p8);
            create(p9);
            return true;
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Products could not be added to database.", e.getTimeStamp());
        }
    }

    @Override
    public HealthProductDTO getById(int id) {
        Product product = null;

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            product = em.find(Product.class, id);
            em.getTransaction().commit();
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Product could not be added to database. Product is null", e.getTimeStamp());
        }
        return convertToDTO(product);
    }

    @Override
    public Set<HealthProductDTO> getByCategory(String category) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<HealthProductDTO> query = (TypedQuery<HealthProductDTO>) em.createQuery(
                    "SELECT new dtos.HealthProductDTO(p.id,p.description,p.name, p.price, p.calories, p.description, p.expireDate) " +
                            "FROM Product p " +
                            "WHERE p.category =:category");
            query.setParameter("category", category);
            List<HealthProductDTO> products = query.getResultList();
            return new HashSet<>(products);
        }catch (DatabaseException e){
         throw new DatabaseException(e.getStatusCode(),"No products found",e.getTimeStamp());
        }
    }

    @Override
    public HealthProductDTO create(Product product) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Product could not be added to database. Product is null", e.getTimeStamp());
        }
        return convertToDTO(product);
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
