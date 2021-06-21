package org.perscholas.service;

import org.perscholas.dao.IBookRepo;
import org.perscholas.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    IBookRepo bookRepo;
    public List<Book> getAllBook(){
        return bookRepo.findAll();

    }
    public void addBook(Book book){
        bookRepo.save(book);

    }
    public void deleteBookById(Long id){
        bookRepo.deleteById(id);
    }


    public Optional<Book> getBookById(Long id){
        return bookRepo.findById(id);
    }

    public List<Book> getAllBooksByCategoryId(int id){
        return bookRepo.findAllByCategory_Id(id);
    }
}
