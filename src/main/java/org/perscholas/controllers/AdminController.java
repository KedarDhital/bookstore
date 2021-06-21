package org.perscholas.controllers;

import org.perscholas.dto.BookDTO;
import org.perscholas.model.Book;
import org.perscholas.model.Category;
import org.perscholas.service.BookService;
import org.perscholas.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/bookImages";
     // to access category service layer so we can post categories
    @Autowired
    CategoryService categoryService;

    @Autowired
    BookService bookService;

    @GetMapping("/admin")
    public String adminPage(){
        return "adminPage";
    }
    // get list of categories object from database

    @GetMapping("/admin/categories")
    public String getCategories(Model model){
        model.addAttribute("categories", categoryService.getAllCategory() );

        return "categories";
    }
    // sends Category object to the front end
    @GetMapping("/admin/categories/add")
    public String addCategories(Model model) {
        model.addAttribute("category", new Category());
        return "addcat";
    }

    // posts Category object in the front end
    @PostMapping("/admin/categories/add")
    public String postCategories(@ModelAttribute("category")Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    // method to delete category by admin, this methods first fetch the category id by using pathvariable named id
    // It calls delete method from catservice and redirects the page to admin/categories.
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id){
    categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";

    }
    // method updates category, it fetches id first and makes it an object
    // Also, if category id doesn't exit , then it will redirect to error page 404.
    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model){
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);
        if(categoryOptional.isPresent()){
            model.addAttribute("category", categoryOptional.get());
            return "addcat";
        } else
            return "404";
    }
    // Book Section
    @GetMapping("/admin/books")
    public String books(Model model){
        model.addAttribute("books" ,bookService.getAllBook());
        return "books";

    }
    @GetMapping("/admin/books/add")
    public String addBooks(Model model){
        model.addAttribute("bookDTO" ,new BookDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "booksAdd";

    }
    /* post mapping to get information from the front end
     Also, product image will be get from the user when it is posted from front-end
     used multipart to get image posted from front-end, request param to get the image
     I need to save Book but we are using BookDTO object, so we need to convert BookDTO object into BOOK object*/
    @PostMapping("/admin/books/add")
    public String bookAddPost(@ModelAttribute("bookDTO") BookDTO bookDTO,
                              @RequestParam("bookImage")MultipartFile file,
                              @RequestParam("imgName") String imgName) throws IOException {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublishedYear(bookDTO.getPublishedYear());
        book.setPrice(bookDTO.getPrice());
        book.setUnitsInStock(bookDTO.getUnitsInStock());
        /* book object has category object but booKdto only has id so
         fetched the category by id and passed id from book dto to getCategoryById method*/

        book.setCategory(categoryService.getCategoryById(bookDTO.getCategoryId()).get());

        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir , imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        book.setImageName(imageUUID);
        bookService.addBook(book);


        return "redirect:/admin/books";

    }

    @GetMapping("/admin/book/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return "redirect:/admin/books";

    }
    @GetMapping("/admin/book/update/{id}")
    public String updateBook(@PathVariable long id, Model model){
        Book book = bookService.getBookById(id).get();
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setCategoryId(book.getCategory().getId());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPublishedYear(book.getPublishedYear());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setUnitsInStock(book.getUnitsInStock());
        bookDTO.setImageName(book.getImageName());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("bookDTO", bookDTO);
        return "booksAdd";

    }

}
