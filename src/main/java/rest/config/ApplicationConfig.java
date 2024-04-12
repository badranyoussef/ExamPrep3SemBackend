package rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public class ApplicationConfig {
//    private ObjectMapper om = new ObjectMapper();
//    private static ApplicationConfig instance;
//    private Javalin app;
//
//    private ApplicationConfig() {
//        // Initialize ObjectMapper
//        ObjectMapper om = new ObjectMapper();
//
//        // Initialize Javalin app
//        app = Javalin.create(config -> {
//            config.http.defaultContentType = "application/json";
//            config.routing.contextPath = "/healthshop/api";
//        });
//    }
//
//    public static ApplicationConfig getInstance() {
//        if (instance == null) {
//            instance = new ApplicationConfig();
//        }
//        return instance;
//    }
//
//    public ApplicationConfig initiateServer() {
//        return instance;
//    }
//
//    public ApplicationConfig startServer(int portNumber) {
//        app.start(portNumber);
//        return instance;
//    }
//
//    public ApplicationConfig setRoute(EndpointGroup route) {
//        app.routes(route);
//        return instance;
//    }
//
//    public ApplicationConfig setExceptionHandlers() {
//
//        app.exception(Exception.class, (e, ctx) -> {
//            ObjectNode node = om.createObjectNode().put("errorMessage", e.getMessage());
//            ctx.status(500).json(node);
//        });
//
//        app.error(404, ctx -> {
//            ctx.status(404).result("Not Found");
//        });
//
//        app.exception(IllegalStateException.class, (e, ctx) -> {
//            ctx.status(400).result("Bad Request: " + e.getMessage());
//        });
//
//        return instance;
//    }
//    public void stopServer() {
//        app.stop();
//    }
}
