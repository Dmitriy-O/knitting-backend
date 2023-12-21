package com.example.knittingback.controller;

import com.example.knittingback.entity.ItemEntity;
import com.example.knittingback.model.*;
import com.example.knittingback.services.Service;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// @Controller, @Repository, @Service are beans
@org.springframework.stereotype.Controller
@CrossOrigin(origins = "http://localhost:3000/")
//@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
//The @RestController annotation tells Spring Boot that this class is a REST controller. This means that it will handle HTTP requests and return responses in JSON format.
@RequestMapping("/api/v1")
//The @RequestMapping("/api/v1") annotation specifies the base URL for all requests handled by this controller. In this case, the base URL is /api/v1.
public class Controller {

    //    @Autowired
    //The @Autowired annotation tells Spring Boot to inject the Service dependency into the Controller class. The Service class is responsible for interacting with the database.
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    //The public Controller(Service service) constructor injects the Service dependency into the Controller class.
//    @PostMapping("/categories")
//    //The @PostMapping("/categories") annotation specifies that the createCategory() method will handle HTTP POST requests to the /categories URL.
//    //The public Category createCategory(@RequestBody Category category) method creates a new category and returns it. The @RequestBody annotation tells Spring Boot to bind the JSON data in the request body to the category parameter.
//    public Category createCategory(@RequestBody Category category) {
//        return service.createCategory(category);
//    }

    @PostMapping("/items")
    public ItemEntity createItem(@RequestParam MultipartFile image, @RequestParam String price, @RequestParam String name, @RequestParam String description) throws IOException {
//        System.out.println("success");
//       return null;
        return service.createItem(image, description, price, name);
    }

//    @PostMapping("/fileSystem")
//    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
//        try {
//            String uploadImageService = service.uploadImageService(image);
//            return ResponseEntity.status(HttpStatus.OK).body(uploadImageService);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }// what the beans is

    @PostMapping("/order")
    public Order createOrder() {
        Order order = new Order().builder().date(LocalDateTime.now()).build();
        return service.createOrder(order);
    }

    @PostMapping("/client")
    public Client createClient(@RequestBody Client client) {
        return service.createClient(client);
    }

    @GetMapping(
            value = "/images/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody ByteArrayResource getImageWithMediaType(@PathVariable("id") long id) throws IOException {
        Image imageData = service.downloadImageService(id);
//    Path path = Paths.get(getClass().getResource(imageData.getFilePath()).toURI());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Path.of(imageData.getFilePath())));

        return resource;
    }


//    @GetMapping("/categories")
//    public List<Category> getAllCategories() {
//        return service.getAllCategories();
//    }

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return service.get_All_Items();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemByID(@PathVariable long id) {
        Item item = service.getItemByID(id);
      return   ResponseEntity.ok(item);

    }
//    @GetMapping("/images")
//    public List<Image> downloadAllimages() {
//        return service.getAllImages();
//
//    }


    @DeleteMapping("/items/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteModels(@PathVariable("id") long id) {
        boolean isDeleted = false;
        isDeleted = service.deleteModel(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", isDeleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestParam MultipartFile image, @RequestParam String price, @RequestParam String name, @RequestParam String description) throws IOException {
        Item item=service.updateItem(id,name,description,price,image);
        return ResponseEntity.ok(item);
//        return null;
    }
}
