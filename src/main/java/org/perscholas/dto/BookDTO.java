package org.perscholas.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.perscholas.model.Category;


/*
 In book class there is a field called Category. We need DTO class to
 handle the category object in the front end. Date Transfer object.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDTO {

    Long id;

    String title;

    String isbn;

    String author;

    int publishedYear;

    double price;

    int unitsInStock;

    String imageName;

    int categoryId;
}
