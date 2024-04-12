package rest.routes;

import controller.HealthProductController;
import io.javalin.apibuilder.EndpointGroup;
import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.get;

public class HealthProductRoutes {

    HealthProductController healthProductController = new HealthProductController();

    //1.4 & 1.4.2 develop rest API
    public EndpointGroup getHealthRoutes() {
        return () -> path("/healthproducts", () -> {

//        frem for at implementere exceptions ved hver endpoint brnytter jeg globale exceptions som sætte op i javalin konfigurationen
//
//            post("/initiate", ctx ->
//            {
//                try {
//                    healthProductController.initiateProducts().handle(ctx);
//                } catch (APIException e) {
//                    ctx.json(Map.of(
//                            "status", e.getStatusCode(),
//                            "message", "API ERROR " + e.getMessage(),
//                            "timestamp", e.getTimeStamp().toString()
//                    ));
//                }
//            });

            // initiate all product
            post("/initiate", ctx -> healthProductController.initiateProducts().handle(ctx));

            // get all products
            get("/", ctx -> healthProductController.getAll().handle(ctx));

            // get a product by id
            get("/{id}", ctx -> healthProductController.getById().handle(ctx));

            // create a product
            post("/", ctx -> healthProductController.create().handle(ctx));

            // update product
            put("/{id}", ctx -> healthProductController.update().handle(ctx));

            // delete product by id
            delete("/{id}", ctx -> healthProductController.delete().handle(ctx));
        });
    }
}
