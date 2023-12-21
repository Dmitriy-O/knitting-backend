package com.example.knittingback.services;

import com.example.knittingback.entity.*;
import com.example.knittingback.model.*;
import com.example.knittingback.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service

//@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})

public class ServiceImpl implements Service {

    final private RepositoryCategory repositoryCategory;
    final private RepositoryItemEntity repositoryItemEntity;
    final private RepositoryOrder repositoryOrder;
    final private RepositoryClient repositoryClient;
    final private RepositoryImagePath repositoryImagePath;

    final private String FOLDER_PATH = "D:\\Projects\\Portfolio\\full-stack\\images\\";

    public ServiceImpl(RepositoryCategory repositoryCategory, RepositoryItemEntity repositoryItemEntity, RepositoryOrder repositoryOrder, RepositoryClient repositoryClient, RepositoryImagePath repositoryImagePath) {
        this.repositoryCategory = repositoryCategory;
        this.repositoryItemEntity = repositoryItemEntity;
        this.repositoryOrder = repositoryOrder;
        this.repositoryClient = repositoryClient;
        this.repositoryImagePath = repositoryImagePath;
    }

    //Конструктор класса ServiceImpl необходим, потому что он является единственным способом передать экземпляр класса Repository в класс ServiceImpl. Если вы не используете конструктор, вам нужно будет вручную внедрить экземпляр класса Repository в класс ServiceImpl. Это может быть утомительно и подвержено ошибкам.
    @Override
    public Category createCategory(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(category, categoryEntity);
        repositoryCategory.save(categoryEntity);
        return category;
    }

    @Override
    public ItemEntity createItem(MultipartFile image, String description, String price, String name) throws IOException {
        ImageEntity uploadImageService = uploadImageService(image);
        double val = Double.parseDouble(price);
        ImageEntity findByName = repositoryImagePath.findByName(image.getOriginalFilename());

        ItemEntity newItemEntity = com.example.knittingback.entity.ItemEntity.builder()
                .name(name)
                .description(description)
                .price(BigDecimal.valueOf(val))
                .image_ID(findByName)
                .build();
        repositoryItemEntity.save(newItemEntity);

        return newItemEntity;
    }

//    @Override
//    public Item createItem(Item item) {
//        String convertedPath = item.getImagePath().replace('/', '\\');
////
//        ItemEntity itemEntity = ItemEntity.builder()
//                .name(item.getName())
//                .description(item.getDescription())
//                .price(item.getPrice())
//                .image_ID(repositoryImagePath.findByFilePath(convertedPath))
////                .id_category(converter.convert(item.getId_category()))
//                .build();
////        itemEntity
//        repositoryItemEntity.save(itemEntity);
//        return item;
//    }

    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId())
                .date(order.getDate()).build();
        repositoryOrder.save(orderEntity);
        return order;
    }

    @Override
    public Client createClient(Client client) {

        ClientEntity clientEntity = ClientEntity.builder()
                .name(client.getName())
                .build();
        repositoryClient.save(clientEntity);
        return client;
    }


    @Override
    public ImageEntity uploadImageService(MultipartFile image) throws IOException {
        String filePath = FOLDER_PATH + image.getOriginalFilename();
        ImageEntity findByName = null;
        try {
            findByName = repositoryImagePath.findByName(image.getOriginalFilename());
        } catch (Exception e) {
            System.out.println("image already exsist");
        }
        image.transferTo(Path.of(filePath));

        if (findByName == null) {
            ImageEntity imageEntity = ImageEntity.builder()
                    .name(image.getOriginalFilename())
                    .type(image.getContentType())
                    .filePath(filePath)
                    .build();
            repositoryImagePath.save(imageEntity);
            return imageEntity;
        } else {
            return findByName;
        }
    }


    @Override
    public Image downloadImageService(long id) throws IOException {
        ImageEntity findByID = repositoryImagePath.findById(id);
        return Image.builder()
                .id(findByID.getId())
                .name(findByID.getName())
                .type(findByID.getType())
                .filePath(findByID.getFilePath())
                .build();
    }

//    public String downloadImageService(long id) {
////        ImageEntity findByID=repositoryImagePath.findById(id);
//        String pathToImageDefault="http://localhost:8080/api/v1/images/";
//
//        return pathToImageDefault+id;
//    }


    @Override
    public Item getItemByID(long id) {

        String pathToImage = "http://localhost:8080/api/v1/images/";
        ItemEntity itemEntity = repositoryItemEntity.findById(id);
        Item returnItem = Item.builder()
                .id(itemEntity.getId())
                .name(itemEntity.getName())
                .description(itemEntity.getDescription())
                .price(itemEntity.getPrice())
                .imagePath(pathToImage + itemEntity.getImage_ID().getId()).build();
        return returnItem;

    }

    @Override
    public List<Item> get_All_Items() {
        List<ItemEntity> categoryEntities
                = repositoryItemEntity.findAll();
        String pathToImage = "http://localhost:8080/api/v1/images/";
        List<Item> itemEntities = categoryEntities.stream()
                .map(item -> Item.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .description(item.getDescription())
                        .price(item.getPrice())
                        .imagePath(pathToImage + item.getImage_ID().getId())
                        .build()).toList();


//downloadImageService()
//        ItemEntity temp= categoryEntities.get(0);
//        pathToImage.append( temp.getImage_ID().getId());
        //Список CategoryEntity содержит объекты класса CategoryEntity, которые представляют записи в таблице categories в базе данных

        //Список Category содержит объекты класса Category, которые представляют представления этих записей.
        return itemEntities;
    }


    @Override
    public boolean deleteModel(long id) {
        ItemEntity itemEntity = repositoryItemEntity.findById(id);
        repositoryItemEntity.delete(itemEntity);
        return true;
    }

    @Override
    public Item updateItem(long id, String name, String description, String price, MultipartFile image) throws IOException {
        ItemEntity itemEntityForUpdate = repositoryItemEntity.findById(id);
        itemEntityForUpdate.setName(name);
        itemEntityForUpdate.setDescription(description);
        itemEntityForUpdate.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        itemEntityForUpdate.setImage_ID(uploadImageService(image));
        repositoryItemEntity.save(itemEntityForUpdate);
        Item returnItem = new Item();
        BeanUtils.copyProperties(itemEntityForUpdate, returnItem);
        return returnItem;
    }
//    @Override
//    public Item updateItem(long id, Item item) {
//        ItemEntity itemEntity=repositoryItemEntity.findById(id).get();
//        itemEntity.setName(item.getName());
//        itemEntity.setDescription(item.getDescription());
//        itemEntity.setPrice(item.getPrice());
//        itemEntity.setImage_ID(uploadImageService());
//
//    }

    @Override
    public Optional<CategoryEntity> getCategoryEntityByID(long categoryID) {

        Optional<CategoryEntity> categoryById = repositoryCategory.findById(categoryID);
        return categoryById;
    }
}

