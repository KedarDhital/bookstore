package org.perscholas.dao;

import org.perscholas.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IBookRepo extends JpaRepository<Book, Long> {

    List<Book> findAllByCategory_Id(int id);
}
