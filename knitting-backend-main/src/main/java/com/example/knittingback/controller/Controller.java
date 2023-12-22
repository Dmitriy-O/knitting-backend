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

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }


    @PostMapping("/items")
    public ItemEntity createItem(@RequestParam MultipartFile image, @RequestParam String price, @RequestParam String name, @RequestParam String description) throws IOException {

        return service.createItem(image, description, price, name);
    }


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
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Path.of(imageData.getFilePath())));

        return resource;
    }


    @GetMapping("/items")
    public List<Item> getAllItems() {
        return service.get_All_Items();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemByID(@PathVariable long id) {
        Item item = service.getItemByID(id);
        return ResponseEntity.ok(item);

    }


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
        Item item = service.updateItem(id, name, description, price, image);
        return ResponseEntity.ok(item);
//        return null;
    }
}
