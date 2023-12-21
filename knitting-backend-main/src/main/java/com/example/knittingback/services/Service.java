package com.example.knittingback.services;

import com.example.knittingback.entity.CategoryEntity;
import com.example.knittingback.entity.ImageEntity;
import com.example.knittingback.model.*;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Service {

    Category createCategory(Category category);
    com.example.knittingback.entity.ItemEntity createItem(@RequestPart MultipartFile image, String description, String price, String name) throws IOException;
    Order createOrder(Order order);
    Client createClient(Client client);
    Optional<CategoryEntity> getCategoryEntityByID(long categoryID);


    ImageEntity uploadImageService(MultipartFile image) throws IOException;
    Image downloadImageService(long id) throws IOException;

    List<Item> get_All_Items();
//    List<Image> getAllImages();

    boolean deleteModel(long id);

//    Item updateItem(long id, Item item);

    Item getItemByID(long id);
//    String downloadImageService(long id);

    Item updateItem(long id, String name, String description, String price, MultipartFile image) throws IOException;
}
