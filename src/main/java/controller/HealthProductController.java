package controller;

import dao.HealthProductDaoMockInMemory;
import dao.HealthProductDaoDB;
import dtos.HealthProductDTO;
import exceptions.APIException;
import io.javalin.http.Handler;
import persistence.model.Product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

//1.4.1
public class HealthProductController implements IHealthProductController {

    private static HealthProductDaoMockInMemory daoInMemmory = new HealthProductDaoMockInMemory();
    private static HealthProductDaoDB daoDB = new HealthProductDaoDB();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String timestamp = dateFormat.format(new Date());


    public Handler initiateProducts() {
        return ctx -> {
            if (daoDB.initiateProducts()) {
                ctx.result("Products initialized");
            } else {
                ctx.status(400).result("Products not initialized");
            }
        };
    }

    @Override
    public Handler getAll() {
        return ctx -> {
            Set<HealthProductDTO> allProducts = daoDB.getAll();
            if (allProducts.isEmpty()) {
                throw new APIException(200, "No products available", timestamp);
            } else {
                ctx.json(allProducts);
            }
        };
    }

    @Override
    public Handler getById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO dto = daoDB.getById(id);
            if (dto == null) {
                throw new APIException(400, "The product you are looking for is not available", timestamp);
            } else {
                ctx.json(dto);
            }
        };
    }

    @Override
    public Handler create() {
        return ctx -> {
            Product product = ctx.bodyAsClass(Product.class);
            HealthProductDTO createdProduct = daoDB.create(product);
            if(createdProduct != null) {
                ctx.status(200).json(createdProduct);
            }else{
                throw new APIException(500, "Failed to create the product", timestamp);
            }
        };
    }

    @Override
    public Handler update() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO productDTO = ctx.bodyAsClass(HealthProductDTO.class);
            productDTO.setId(id);
            HealthProductDTO updatedProduct = daoDB.update(productDTO);
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
            HealthProductDTO productDTO = daoDB.delete(id);
            if (productDTO == null) {
                ctx.status(400).result("Product not found");
            } else {
                ctx.status(200).json(productDTO);
            }
        };
    }
}
