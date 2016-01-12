package com.company.task2.dao;

import com.company.task2.entity.Author;
import com.company.task2.entity.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ira on 12.01.2016.
 */
public interface AuthorDao {

    void create(Author author);
    Author read(int idAuthor);
    void delete(String Surname, String name);
    void delete(Author author);
    void update(Book author);
    List<Author> findBySurname(String surname);
    List<Author> getAll();
    //String getSurnameById(int idAuthor);

}
