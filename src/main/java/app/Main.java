package app;

import appConfig.Application;
import rest.routes.Route;

public class Main {

    public static void main(String[] args) {
        Route routes = new Route();

        //1.1 creating java-project using javalin framework
        Application healthStoreApp = Application.getInstance();
        healthStoreApp.startServer(7070);
        healthStoreApp.setExceptionHandlers();
        healthStoreApp.setRoute(routes.getHealthRoutes());
        healthStoreApp.setRoute(routes.getStorageRoutes());

    }
}