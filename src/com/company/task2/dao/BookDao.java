package com.company.task2.dao;

import com.company.task2.entity.Book;

import java.util.List;

/**
 * Created by Ira on 11.01.2016.
 */
public interface BookDao {


    //��������� ������ � ��
   void save (Book bookEntity);

    Book findById(int idBook);
    Book findByName(String bookName);

    //������� ������ �� �������
   // public void delete(String bookName);
    void delete(Book book);
    void update(Book book); // ���������� ���������� �����
    List<Book> findAll();




}
