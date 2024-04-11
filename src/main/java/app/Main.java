package app;

import dao.HealthProductDAOMock;
import io.javalin.Javalin;
import persistence.model.Product;
import rest.routes.HealthProductRoutes;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //1.1 creating java-project using javalin framework
        HealthProductRoutes healthProductRoutes = new HealthProductRoutes();
        Javalin healthStore = Javalin.create().start(7070);
        healthStore.routes(healthProductRoutes.getHealthRoutes());
    }
}