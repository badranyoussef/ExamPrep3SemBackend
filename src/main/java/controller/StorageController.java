package controller;

import dao.HealthProductDaoDB;
import dao.HealthProductDaoMockInMemory;
import dao.StorageDao;
import dtos.HealthProductDTO;
import dtos.StorageDTO;
import exceptions.APIException;
import io.javalin.http.Handler;
import persistence.model.Product;
import persistence.model.Storage;

import javax.security.auth.callback.CallbackHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

//1.4.1
public class StorageController implements IHealthProductController {

    private static StorageDao dao = new StorageDao();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String timestamp = dateFormat.format(new Date());


    public Handler initiateProducts() {
        return ctx -> {
            if (dao.initiateProducts()) {
                ctx.result("Storage initialized");
            } else {
                ctx.status(400).result("Products not initialized");
            }
        };
    }

    @Override
    public Handler getAll() {
        return ctx -> {
            Set<StorageDTO> storages = dao.getAll();
            if (storages.isEmpty()) {
                throw new APIException(200, "No products available", timestamp);
            } else {
                ctx.json(storages);
            }
        };
    }

    @Override
    public Handler getById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            StorageDTO dto = dao.getById(id);
            if (dto != null) {
                ctx.json(dto);
            } else {
                throw new APIException(400, "The product you are looking for is not available", timestamp);
            }
        };
    }

    @Override
    public Handler create() {
        return ctx -> {
            Storage storage = ctx.bodyAsClass(Storage.class);
            StorageDTO createdProduct = dao.create(storage);
            if(createdProduct != null) {
                ctx.status(200).json(createdProduct);
            }else{
                throw new APIException(500, "Failed to create the storage", timestamp);
            }
        };
    }

    @Override
    public Handler update() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            StorageDTO storageDTO = ctx.bodyAsClass(StorageDTO.class);
            storageDTO.setId(id);
            StorageDTO updatedStorage = dao.update(storageDTO);
            if (updatedStorage != null) {
                ctx.json(updatedStorage);
            } else {
                throw new APIException(ctx.statusCode(), "No data found.", "" + timestamp);
            }
        };
    }


    @Override
    public Handler delete() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            StorageDTO storageDTO = dao.delete(id);
            if (storageDTO != null) {
                ctx.status(200).json(storageDTO);
            } else {
                throw new APIException(ctx.statusCode(), "Storage not found", timestamp);
            }
        };
    }

    public Handler addProductToStorage() {
        return ctx ->{
          int storageId = Integer.parseInt(ctx.pathParam("id_storage"));
          int productId = Integer.parseInt(ctx.pathParam("id_product"));
          HealthProductDTO dto = dao.addProductToStorage(storageId,productId);
          if(dto != null){
              ctx.json(dto);
          }else{
              throw new APIException(ctx.statusCode(),"Unable to add product to storage", timestamp);
          }
        };
    }


    public Handler getProductsByStorageShelf() {
        return ctx -> {
            int storageId = Integer.parseInt(ctx.pathParam("id"));

            Set<Product> products = dao.getProductsByStorageShelf(storageId);
            if(products != null){
                ctx.json(products);
            }else{
                throw new APIException(ctx.statusCode(), "No products available", timestamp);
            }

        };
    }
}
