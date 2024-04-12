package app;

import exceptions.APIException;
import exceptions.AppLogger;
import exceptions.DatabaseException;
import io.javalin.Javalin;
import rest.routes.HealthProductRoutes;
import java.util.Map;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = AppLogger.getLogger();

    public static void main(String[] args) {

        //1.1 creating java-project using javalin framework
        HealthProductRoutes healthProductRoutes = new HealthProductRoutes();
        Javalin healthStoreApp = Javalin.create().start(7070);

        healthStoreApp.routes(healthProductRoutes.getHealthRoutes());

        //2.2 error handling. I'm implementing global error handling. Controller and DAO throws the exceptions
        healthStoreApp.exception(APIException.class, (e, ctx) -> {
            logger.warning(
                    "HTTP: "+ctx.status()+" Time: "+e.getTimeStamp()+" Method: "+ctx.method()+" Path: "+ctx.path()+" Client IP: "+ctx.ip()+"\n"
            );
//            logger.warning(String.format("HTTP %d - %s - %s %s, Klientens IP: %s, Tidspunkt: %tc",
//                    ctx.status(),
//                    e.getMessage(),
//                    ctx.method(),
//                    ctx.path(),
//                    ctx.ip(),
//                    System.currentTimeMillis()));
            ctx.status(e.getStatusCode());
            ctx.json(Map.of(
                    "status", e.getStatusCode(),
                    "api error", e.getMessage(),
                    "timestamp", e.getTimeStamp().toString()
            ));
        });
        healthStoreApp.exception(DatabaseException.class, (e, ctx) -> {
            logger.warning(String.format("HTTP %d - %s - %s %s, Klientens IP: %s, Tidspunkt: %tc",
                    ctx.status(),
                    e.getMessage(),
                    ctx.method(),
                    ctx.path(),
                    ctx.ip(),
                    System.currentTimeMillis()));
            ctx.status(e.getStatusCode());
            ctx.json(Map.of(
                    "status", e.getStatusCode(),
                    "database error", e.getMessage(),
                    "timestamp", e.getTimeStamp().toString()
            ));
        });
    }
}