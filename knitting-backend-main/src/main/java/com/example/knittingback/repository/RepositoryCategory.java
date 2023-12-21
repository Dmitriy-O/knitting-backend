package com.example.knittingback.repository;

import com.example.knittingback.entity.CategoryEntity;
import com.example.knittingback.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
    /**
     JpaRepository - это интерфейс, предоставляемый Spring Data JPA. Он используется для взаимодействия с базой данных JPA.

     Интерфейс JpaRepository имеет несколько методов, которые можно использовать для выполнения операций с базой данных, таких как:

     save(): сохраняет сущность в базе данных.
     findById(): находит сущность по ее идентификатору.
     findAll(): находит все сущности.
     count(): возвращает количество сущностей.
     Интерфейс JpaRepository принимает два аргумента:

     Entity: тип сущности, с которой вы хотите взаимодействовать.
     ID: тип идентификатора сущности.
     */
@org.springframework.stereotype.Repository
public interface RepositoryCategory extends JpaRepository<CategoryEntity,Long> {

}
//    @org.springframework.stereotype.Repository
//    public interface RepositoryItem extends JpaRepository<ItemEntity,Long> {
//}
