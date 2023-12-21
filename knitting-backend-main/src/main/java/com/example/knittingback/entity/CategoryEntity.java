package com.example.knittingback.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Модель и сущность - это два важных понятия в объектно-ориентированном программировании. Они часто используются в контексте базы данных.
 * <p>
 * Сущность - это объект, который представляет запись в таблице базы данных. Сущности имеют набор атрибутов, которые соответствуют столбцам в таблице базы данных.
 * <p>
 * Модель - это представление сущности. Модель может содержать дополнительные данные, которые не хранятся в базе данных. Например, модель может содержать данные, необходимые для отображения сущности в пользовательском интерфейсе.
 * В целом, модели и сущности используются для разных целей. Сущности используются для доступа к данным в базе данных. Модели используются для представления этих данных в приложении.
 * <p>
 * Вот несколько дополнительных различий между моделями и сущностями:
 * <p>
 * Модели обычно являются более сложными, чем сущности. Они могут содержать дополнительные данные, которые не хранятся в базе данных.
 * Модели обычно используются для отображения данных в пользовательском интерфейсе. Они могут содержать методы, которые используются для отображения данных в пользовательском интерфейсе.
 * Сущности обычно используются для доступа к данным в базе данных. Они могут содержать методы, которые используются для доступа к данным в базе данных.
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    The GenerationType.IDENTITY strategy means that the database will automatically increment the value of the primary key each time a new record is inserted.
    private long id;
    private int number;
    @Column(length=1000)
    private String image;
    private String name;

    //mapped by uses field :id_category from ItemEntity
//    @OneToMany(mappedBy ="id_category",cascade = CascadeType.ALL )
//    private List<ItemEntity> items;


}
