package rest.routes;

import controller.HealthProductController;
import exceptions.APIException;
import io.javalin.apibuilder.EndpointGroup;

import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.get;

public class HealthProductRoutes {

    HealthProductController healthProductController = new HealthProductController();

    //1.4 & 1.4.2 develop rest API
    public EndpointGroup getHealthRoutes() {
        return () -> path("/healthstore/api/healthproducts", () -> {

            // initiate products
            post("/initiate", ctx -> {
                try {
                    healthProductController.initiateProducts().handle(ctx);
                } catch (APIException e) {
                    ctx.json(Map.of(
                            "status", e.getStatusCode(),
                            "message", "API ERROR " + e.getMessage(),
                            "timestamp", e.getTimeStamp().toString()
                    ));
                }
            });

            // get all products
            get("/", ctx -> {
                try {
                    healthProductController.getAll().handle(ctx);
                } catch (APIException e) {
                    ctx.json(Map.of(
                            "status", e.getStatusCode(),
                            "message", "API ERROR " + e.getMessage(),
                            "timestamp", e.getTimeStamp().toString()
                    ));
                }
            });

            // get a product by id
            get("/{id}", ctx ->{
                try { healthProductController.getById().handle(ctx);
                } catch (APIException e) {
                    ctx.json(Map.of(
                            "status", e.getStatusCode(),
                            "message", "API ERROR " + e.getMessage(),
                            "timestamp", e.getTimeStamp().toString()
                    ));
                }
            });

            // create a product
            post("/", ctx -> {
                try {healthProductController.create().handle(ctx);
                } catch (APIException e) {
                    ctx.json(Map.of(
                            "status", e.getStatusCode(),
                            "message", "API ERROR " + e.getMessage(),
                            "timestamp", e.getTimeStamp().toString()
                    ));
                }
            });

            // update product
            put("/{id}", ctx -> {
                try {healthProductController.update().handle(ctx);
                } catch (APIException e) {
                    ctx.json(Map.of(
                            "status", e.getStatusCode(),
                            "message", "API ERROR " + e.getMessage(),
                            "timestamp", e.getTimeStamp().toString()
                    ));
                }
            });

            // delete product by id
            delete("/{id}", ctx -> {
                try {healthProductController.delete().handle(ctx);
                } catch (APIException e) {
                    ctx.json(Map.of(
                            "status", e.getStatusCode(),
                            "message", "API ERROR " + e.getMessage(),
                            "timestamp", e.getTimeStamp().toString()
                    ));
                }
            });
        });
    }
}
