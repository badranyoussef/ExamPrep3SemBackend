package app;

import appConfig.Application;
import jakarta.persistence.EntityManagerFactory;
import persistence.config.HibernateConfig;
import rest.routes.HealthProductRoutes;

public class Main {

    public static void main(String[] args) {

        HealthProductRoutes routes = new HealthProductRoutes();

        //1.1 creating java-project using javalin framework
        Application healthStoreApp = Application.getInstance();
        healthStoreApp.startServer(7070);
        healthStoreApp.setExceptionHandlers();
        healthStoreApp.setRoute(routes.getHealthRoutes());

    }
}