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

     //   DBConnection dbConnection = new DBConnection("test","root","JustROOT");
       // dbConnection.selectInformation();
     //   dbConnection.insertRow("Ivanov",20);
      //  dbConnection.closeConnection();
	// write your code here

        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.initPoolData();

        Statement statement = connectionPool.takeConnection().createStatement();
        BookDao bookDao = new MySQLBookDao(connectionPool.takeConnection(),statement);
        Book book = new Book();
        book.init("Boris Godunov",1);
        bookDao.create(book);
        bookDao.create(book);
        bookDao.delete(book);
        List<Book> books = bookDao.getAll();
        bookDao.delete(books.get(2));
        AuthorDao authorDao = new MySQLAuthorDao(connectionPool.takeConnection(), statement);
        List<Author> authors = authorDao.getAll();
        authorDao.delete(authors.get(1));
        connectionPool.closeConnection(connectionPool.takeConnection(),statement);
        //Author author =
       // connectionPool.closeConnection(connectionPool.takeConnection(),connectionPool.);
       // bookDao.read(1).show();
      //  bookDao.delete(book);
      /*  for(Book book: bookDao.getAll()){
            System.out.println(book.toString());
        }

        for(Author author: authorDao.findBySurname("Pushkin")){
            System.out.println(author.toString());
        }*/
       // System.out.println( authorDao.read(1).toString());

        //List<Author>



       // connectionPool.takeConnection()
    }
}
