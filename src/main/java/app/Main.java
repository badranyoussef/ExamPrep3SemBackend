package app;

import dao.HealthProductDaoDB;
import dao.StorageDao;

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