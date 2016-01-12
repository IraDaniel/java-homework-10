package com.company.task2.dao;

import com.company.task2.entity.Book;

import java.util.List;

/**
 * Created by Ira on 11.01.2016.
 */
public interface BookDao {


    //Создает новую запись и соответствующий ей объект
    void create(Book bookEntity);

    Book read(int idBook);

    //Удаляет запись об объекте
    public void delete(String bookName);
    public void delete(Book book);
    void update(Book book);
    List<Book> getAll();




}
