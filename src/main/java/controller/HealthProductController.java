package controller;

import dao.HealthProductDAOMock;
import dtos.HealthProductDTO;
import exceptions.APIException;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import persistence.model.Product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//1.4.1
public class HealthProductController implements IHealthProductController {

    HealthProductDAOMock dao = new HealthProductDAOMock();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String timestamp = dateFormat.format(new Date());
    private static HealthProductDAOMock healthProductDAOMock = new HealthProductDAOMock();


    public Handler initiateProducts() {
        return ctx -> {
            if (dao.initiateProducts()) {
                ctx.result("Products initialized");
            } else {
                ctx.status(400).result("Products not initialized");
            }
        };
    }

    @Override
    public Handler getAll() {
        return ctx -> {
            if (dao.getAll().isEmpty()) {
                ctx.status(400).result("No products available");
            } else {
                List<HealthProductDTO> allProducts = dao.getAll().stream().map(product -> HealthProductDTO.builder()
                                .id(product.getId())
                                .category(product.getCategory())
                                .name(product.getName())
                                .calories(product.getCalories())
                                .price(product.getPrice())
                                .description(product.getDescription())
                                .expireDate(product.getExpireDate())
                                .build())
                        .toList();
                ctx.json(allProducts);
            }
        };
    }

    @Override
    public Handler getById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO dto = dao.getById(id);
            if (dto == null) {
                ctx.status(400).result("The product you are looking for is not available");
            } else {
                ctx.json(dto);
            }
        };
    }

    @Override
    public Handler create() {
        return ctx -> {
            Product product = ctx.bodyAsClass(Product.class);
            if (product == null) {
                ctx.status(HttpStatus.BAD_REQUEST).result("Unable to stores new product");
            } else {
                dao.create(product);
                ctx.json(product);
            }
        };
    }

    @Override
    public Handler update() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO productDTO = ctx.bodyAsClass(HealthProductDTO.class);
            productDTO.setId(id);
            HealthProductDTO updatedProduct = dao.update(productDTO);
            if (updatedProduct != null) {
                ctx.json(updatedProduct);
            } else {
                throw new APIException(400, "No data found.", "" + timestamp);
            }

        };
    }

    @Override
    public Handler delete() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if(dao.getProducts().containsKey(id)){
                HealthProductDTO productDTO = dao.delete(id);
                ctx.json(productDTO).result("Product successfully deleted").status(201);
            }else{
                throw new APIException(400, "Product not found.", "" + timestamp);
            }
        };
    }
}
