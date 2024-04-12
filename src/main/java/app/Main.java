package app;

import appConfig.Application;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import persistence.config.HibernateConfig;
import persistence.model.Product;
import rest.routes.HealthProductRoutes;

import java.time.LocalDate;

public class Main {
//    private static final Logger logger = AppLogger.getLogger();

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Product("Mineral", "Magnesium", 63.36, 229, "Magnesium for bedre sundhed og velvære.", LocalDate.of(2024, 6, 20)));
        em.getTransaction().commit();


//        HealthProductRoutes routes = new HealthProductRoutes();
//
//        //1.1 creating java-project using javalin framework
//        Application healthStoreApp = Application.getInstance();
//        healthStoreApp.startServer(7070);
//        healthStoreApp.setExceptionHandlers();
//        healthStoreApp.setRoute(routes.getHealthRoutes());


//        //1.1 creating java-project using javalin framework
//        HealthProductRoutes healthProductRoutes = new HealthProductRoutes();
//        Javalin healthStoreApp = Javalin.create().start(7070);
//
//        healthStoreApp.routes(healthProductRoutes.getHealthRoutes());
//
//        //2.2 error handling. I'm implementing global error handling. Controller and DAO throws the exceptions
//        healthStoreApp.exception(APIException.class, (e, ctx) -> {
//            logger.warning(
//                    "HTTP: "+ctx.status()+" Time: "+e.getTimeStamp()+" Method: "+ctx.method()+" Path: "+ctx.path()+" Client IP: "+ctx.ip()+"\n"
//            );
//            ctx.status(e.getStatusCode());
//            ctx.json(Map.of(
//                    "status", e.getStatusCode(),
//                    "api error", e.getMessage(),
//                    "timestamp", e.getTimeStamp().toString()
//            ));
//        });
//        healthStoreApp.exception(DatabaseException.class, (e, ctx) -> {
//            logger.warning(
//                    "HTTP: "+ctx.status()+" Time: "+e.getTimeStamp()+" Method: "+ctx.method()+" Path: "+ctx.path()+" Client IP: "+ctx.ip()+"\n"
//            );
//            ctx.status(e.getStatusCode());
//            ctx.json(Map.of(
//                    "status", e.getStatusCode(),
//                    "database error", e.getMessage(),
//                    "timestamp", e.getTimeStamp().toString()
//            ));
//        });
    }
}