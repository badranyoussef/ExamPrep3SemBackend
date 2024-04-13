package rest.routes;

import appConfig.Application;
import dao.StorageDao;
import io.javalin.http.ContentType;
import io.restassured.RestAssured;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import persistence.config.HibernateConfig;
import persistence.model.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

class RouteTest {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(true);
    private static Application app;
    private static int port = 7070;
    private static Route route = new Route();
    private StorageDao dao = new StorageDao();
    private static Storage s1;
    private static Storage s2;

    @BeforeAll
    static void setUpBeforeAll() {
        RestAssured.baseURI = "http://localhost:7070/healthstore/api";
        app = Application.getInstance();
        app.startServer(port)
                .setExceptionHandlers()
                .setRoute(route.getStorageRoutes());
    }

    @BeforeEach
    void setUp() {
        s1 = new Storage(null, 0, 1);
        s2 = new Storage(null, 0, 1);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(s1);
        em.persist(s2);
        em.getTransaction().commit();

    }

    @AfterEach
    void tearDownAfterEach() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Storage ").executeUpdate();
            em.getTransaction().commit();
        }
    }

    @AfterAll
    static void tearDown() {
        emf.close();
        app.stopServer();
    }

    @Test
    @DisplayName("Testing is timestamp is updated when persisting")
    public void test0() {

        int storageId = 1;

        List<Integer> excpected = Arrays.asList(2024, 4, 13); // Eksempel på den faktiske liste

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/storages/{id}", storageId)
                .then()
                .statusCode(200) // Skift til den relevante statuskode
                .body("updatedTimeStamp", notNullValue())
                .body("updatedTimeStamp", equalTo(excpected)); // Skift til den relevante attribut
    }

    @Test
    @DisplayName("Testing is timestamp is updated when persisting")
    public void test0() {

        int storageId = 1;

        List<Integer> excpected = Arrays.asList(2024, 4, 13); // Eksempel på den faktiske liste

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/storages/{id}", storageId)
                .then()
                .statusCode(200) // Skift til den relevante statuskode
                .body("updatedTimeStamp", notNullValue())
                .body("updatedTimeStamp", equalTo(excpected)); // Skift til den relevante attribut
    }

}