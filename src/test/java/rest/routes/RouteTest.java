package rest.routes;

import appConfig.Application;
import dao.StorageDao;
import io.javalin.http.ContentType;
import io.restassured.RestAssured;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import persistence.config.HibernateConfig;
import persistence.model.Product;
import persistence.model.Storage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
    private static Product p1;
    private static Product p2;
    private static Product p3;

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
        p1 = new Product("Mineral", "Magnesium", 63.36, 229, "Magnesium for bedre sundhed og velvære.", LocalDate.of(2024, 6, 20));
        p2 = new Product("Vitamin", "Multivitamin", 25.97, 101, "Multivitamin for bedre sundhed og velvære.", LocalDate.of(2025, 4, 5));
        p3 = new Product("Fiber", "Psyllium Husk", 64.59, 91, "Psyllium Husk for bedre sundhed og velvære.", LocalDate.of(2025, 2, 21));

        s1.addProduct(p1);
        s1.addProduct(p2);
        s1.addProduct(p3);

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
            em.createQuery("DELETE FROM Product ").executeUpdate();
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
    public void test1() {

        int storageId = 1;

        List<Integer> excpected = Arrays.asList(2024, 4, 14); // Eksempel på den faktiske liste - husk at rette dato til

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
    @DisplayName("Testing Post method - create storage")
    public void test2() {
        int expectedTotalAmount = 0;
        int expectedshelfNumber = 2;


        String setBody = "{\"shelfNumber\": \"2\"}";

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(setBody)
                .when()
                .post("/storages")
                .then()
                .statusCode(200)
                .body("shelfNumber", equalTo(expectedshelfNumber))
                .body("totalAmount",equalTo(expectedTotalAmount))
                .extract().response().prettyPrint();

    }

    @Test
    @DisplayName("Testing that 2 storages er created and saved in DB")
    public void test3() {
        int expectedSize = 2;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/storages")
                .then()
                .statusCode(200)
                .body("size()",equalTo(expectedSize));

    }

    @Test
    @DisplayName("Testing that 2 storages er created and saved in DB")
    public void test4() {
        int expectedSize = 2;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/storages")
                .then()
                .statusCode(200)
                .body("products.size()",equalTo(expectedSize));
    }
}