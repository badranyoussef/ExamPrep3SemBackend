package dao;

import dtos.HealthProductDTO;
import dtos.StorageDTO;
import exceptions.DatabaseException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import persistence.config.HibernateConfig;
import persistence.model.Product;
import persistence.model.Storage;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StorageDao implements iDAO<StorageDTO, Storage> {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    HealthProductDaoDB productDao = new HealthProductDaoDB();

    @Override
    public Set<StorageDTO> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT s FROM Storage s";
            TypedQuery<Storage> query = em.createQuery(jpql, Storage.class);
            Set<StorageDTO> result = query.getResultList().stream()
                    .map(this::convertToDTO)// Konverter hvert Product til en HealthProductDTO, bruger this for at indikerer at det er klassens egen metode der bruges
                    .collect(Collectors.toSet());
            return result;
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Unable to get products within 2 weeks expiration", e.getTimeStamp());
        }
    }

    @Override
    public StorageDTO getById(int id) {
        Storage storage = null;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            storage = em.find(Storage.class, id);
            em.getTransaction().commit();
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Storage could not be added to database. Product is null", e.getTimeStamp());
        }
        return convertToDTO(storage);
    }

    @Override
    public StorageDTO create(Storage storage) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(storage);
            em.getTransaction().commit();
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Product could not be added to database. Product is null", e.getTimeStamp());
        }
        return convertToDTO(storage);
    }

    @Override
    public StorageDTO update(StorageDTO storageDTO) {
        Storage storage = new Storage(storageDTO.getId(), storageDTO.getUpdatedTimeStamp(), storageDTO.getTotalAmount(), storageDTO.getShelfNumber(), storageDTO.getProducts());
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(storage);
            em.getTransaction().commit();
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Was unable to update product in database", e.getTimeStamp());
        }
        return storageDTO;
    }


    @Override
    public StorageDTO delete(int id) {
        StorageDTO storageDTO = getById(id);
        Storage storage = new Storage(storageDTO.getId(), storageDTO.getUpdatedTimeStamp(), storageDTO.getTotalAmount(), storageDTO.getShelfNumber(), storageDTO.getProducts());
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(storage);
            em.getTransaction().commit();
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Unable to delete storage from database. Product might not be in DB", e.getTimeStamp());
        }
        return storageDTO;
    }

    public HealthProductDTO addProductToStorage(int storageId, int productId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Storage storage = em.find(Storage.class, storageId);
            Product product = em.find(Product.class, productId);
            product.setStorage(storage);
            storage.addProduct(product);
            em.merge(storage);
            em.getTransaction().commit();
            return productDao.convertToDTO(product);
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Unable to add product to storage", e.getTimeStamp());
        }
    }

    public Set<Product> getProductsByStorageShelf(int storageId) {
        try (EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT p FROM Product p WHERE p.storage.id = :storageId";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("storageId", storageId);
            Set<Product> products = new HashSet<>(query.getResultList());
            return products;
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Unable to retrieve products from storage shelf: " + e.getMessage(), e.getTimeStamp());
        }
    }

    public StorageDTO convertToDTO(Storage storage) {
        return StorageDTO.builder()
                .id(storage.getId())
                .updatedTimeStamp(storage.getUpdatedTimeStamp())
                .totalAmount(storage.getTotalAmount())
                .shelfNumber(storage.getShelfNumber())
                .products(storage.getProducts())
                .build();
    }

    public Storage convertToEntity(StorageDTO storageDTO) {
        return Storage.builder()
                .id(storageDTO.getId())
                .updatedTimeStamp(storageDTO.getUpdatedTimeStamp())
                .totalAmount(storageDTO.getTotalAmount())
                .shelfNumber(storageDTO.getShelfNumber())
                .products(storageDTO.getProducts())
                .build();
    }

    public boolean initiateProducts() {

        Storage s1 = new Storage(LocalDate.now(), 10,2);
        Storage s2 = new Storage(LocalDate.now(), 5,10);

        try {
            create(s1);
            create(s2);
            return true;
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getStatusCode(), "Storage could not be added to database.", e.getTimeStamp());
        }

    }
}
