package com.company.task2.dao;

import com.company.task2.entity.Author;
import com.company.task2.entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ira on 12.01.2016.
 */
public interface AuthorDao {

    void save(Author author);

    Author findById(int idAuthor);
    List<Author> findAll();
    List<Author> findBySurname(String Surname);
    Author findByBook(Book book);

    void update(Author author);

    void delete(Author author);







}
