package app;

import appConfig.Application;
import dao.HealthProductDaoDB;
import dao.StorageDao;
import jakarta.persistence.EntityManagerFactory;
import persistence.config.HibernateConfig;
import persistence.model.Product;
import persistence.model.Storage;
import rest.routes.HealthProductRoutes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        HealthProductDaoDB dao = new HealthProductDaoDB();
        StorageDao daoS = new StorageDao();

        System.out.println(daoS.getProductsByStorageShelf(1));


//        HealthProductRoutes routes = new HealthProductRoutes();
//
//        //1.1 creating java-project using javalin framework
//        Application healthStoreApp = Application.getInstance();
//        healthStoreApp.startServer(7070);
//        healthStoreApp.setExceptionHandlers();
//        healthStoreApp.setRoute(routes.getHealthRoutes());

    }
}