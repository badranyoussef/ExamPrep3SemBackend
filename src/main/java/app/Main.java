package app;

import dao.HealthProductDAOMock;
import exceptions.APIException;
import io.javalin.Javalin;
import persistence.model.Product;
import rest.routes.HealthProductRoutes;

import java.time.LocalDate;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //1.1 creating java-project using javalin framework
        HealthProductRoutes healthProductRoutes = new HealthProductRoutes();
        Javalin healthStoreApp = Javalin.create().start(7070);

        healthStoreApp.routes(healthProductRoutes.getHealthRoutes());
        healthStoreApp.exception(APIException.class, (e, ctx) -> {
            ctx.status(e.getStatusCode());
            ctx.json(Map.of(
                    "status", e.getStatusCode(),
                    "message", "API ERROR " + e.getMessage(),
                    "timestamp", e.getTimeStamp().toString()
            ));
        });


    }
}