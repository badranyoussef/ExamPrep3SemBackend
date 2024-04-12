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
import java.util.stream.Collectors;

public class HealthProductDaoDB implements iDAO<HealthProductDTO, Product> {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

    @Override
    public Set<HealthProductDTO> getAll() {
        try(EntityManager em = emf.createEntityManager()){
            String jpql = "SELECT p FROM Product p";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            Set<HealthProductDTO> result = query.getResultList().stream()
                    .map(this::convertToDTO)// Konverter hvert Product til en HealthProductDTO, bruger this for at indikerer at det er klassens egen metode der bruges
                    .collect(Collectors.toSet());
            return result;
        }catch(DatabaseException e){
            throw new DatabaseException(e.getStatusCode(),"Unable to get products within 2 weeks expiration", e.getTimeStamp());
        }
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
        Product product = convertToEntity(healthProductDTO);
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        }catch (DatabaseException e){
            throw new DatabaseException(e.getStatusCode(),"Was unable to update product in database",e.getTimeStamp());
        }
        return healthProductDTO;
    }

    @Override
    public HealthProductDTO delete(int id) {
        HealthProductDTO productDTO = getById(id);
        Product product = convertToEntity(productDTO);
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
        }catch (DatabaseException e){
            throw new DatabaseException(e.getStatusCode(), "Unable to delete product from database. Product might not be in DB", e.getTimeStamp());
        }
        return productDTO;
    }


    public Set<HealthProductDTO> getTwoWeeksToExpire() {
        try(EntityManager em = emf.createEntityManager()){
            LocalDate today = LocalDate.now();
            LocalDate twoWeeksAhead = today.plusWeeks(2);
            String jpql = "SELECT p FROM Product p WHERE p.expireDate <= :twoWeeksAhead AND p.expireDate >= :today";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("today", today);
            query.setParameter("twoWeeksAhead", twoWeeksAhead);
            Set<HealthProductDTO> result = query.getResultList().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toSet());
            return result;
        }catch(DatabaseException e){
            throw new DatabaseException(e.getStatusCode(),"Unable to get products within 2 weeks expiration", e.getTimeStamp());
        }
    }


    public List<HealthProductDTO> productsLessThan50Calories() {
        try(EntityManager em = emf.createEntityManager()){
            String jpql = "SELECT p FROM Product p WHERE p.calories < :calories";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("calories", "5");
            List<HealthProductDTO> result = query.getResultList().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return result;
        }catch(DatabaseException e){
            throw new DatabaseException(e.getStatusCode(),"Unable to get products within with less than 50 calories", e.getTimeStamp());
        }
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
