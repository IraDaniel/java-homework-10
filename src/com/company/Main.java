package com.company;

import com.company.task2.connection.ConnectionPool;
import com.company.task2.connection.ConnectionPoolException;
import com.company.task2.dao.AuthorDao;
import com.company.task2.dao.BookDao;
import com.company.task2.entity.Author;
import com.company.task2.entity.Book;
import com.company.task2.mysql.MySQLAuthorDao;
import com.company.task2.mysql.MySQLBookDao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ConnectionPoolException,SQLException {



        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.initPoolData();

        Statement statement = connectionPool.takeConnection().createStatement();
        BookDao bookDao = new MySQLBookDao(connectionPool.takeConnection(),statement);
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();

        book1.init("Boris Godunov",1);
        book2.init("Ruslan and Ludmila", 1);
        book3.init("Romeo and Juliet", 2);

        bookDao.save(book1);
        bookDao.save(book2);
        bookDao.save(book3);

        for( Book book: bookDao.findAll()){
            System.out.println(book);
        }

        AuthorDao authorDao = new MySQLAuthorDao(connectionPool.takeConnection(), statement);

        for(Author author: authorDao.findAll()){
            System.out.println(author);
        }

        connectionPool.closeConnection(connectionPool.takeConnection(),statement);


    }
}
