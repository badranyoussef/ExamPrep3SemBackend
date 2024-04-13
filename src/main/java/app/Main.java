package app;

import appConfig.Application;
import dao.HealthProductDaoDB;
import dao.StorageDao;
import rest.routes.Route;

public class Main {

    public static void main(String[] args) {
//        HealthProductDaoDB dao = new HealthProductDaoDB();
//        StorageDao daoS = new StorageDao();
//
//        System.out.println(daoS.getProductsByStorageShelf(1));


        Route routes = new Route();

        //1.1 creating java-project using javalin framework
        Application healthStoreApp = Application.getInstance();
        healthStoreApp.startServer(7070);
        healthStoreApp.setExceptionHandlers();
        healthStoreApp.setRoute(routes.getHealthRoutes());
        healthStoreApp.setRoute(routes.getStorageRoutes());

    }
}