package com.company.task2.mysql;

import com.company.task2.entity.Book;
import com.company.task2.dao.BookDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ira on 12.01.2016.
 */
public class MySQLBookDao implements BookDao {

    private Connection connection;
    private Statement statement;

    public MySQLBookDao(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public List<Book> getAll(){
        List<Book> books = new ArrayList<>();
        try {
            String sql = "SELECT id,name,idAuthor FROM book";
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setIdAuthor(resultSet.getInt("idAuthor"));
                books.add(book);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void create(Book book){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int id = getNextId();
            book.setId(id);
            String sql = ("INSERT INTO Book VALUES (" + id + ",'" + book.getName() + "'," + book.getIdAuthor() + ")");
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{

        }

    }

    /**
     * Получить информацию о книге по id
     * @param idBook
     * @return
     */
    public Book read(int idBook){
        Book book =  new Book();
        try {
            String sql = "SELECT id,name,idAuthor FROM book where id=" + idBook;
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.first();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setIdAuthor(resultSet.getInt("idAuthor"));
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    //Удаляет запись об объекте по имени
    public void delete(String bookName){

        try {
            String sql = "delete FROM book where name= '" + bookName + "'";
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println(statement.executeUpdate(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Book book){
        try {
            String sql = "delete FROM book where id = " + book.getId() + " and name= '" + book.getName() + "'";
            System.out.println(sql);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println(statement.executeUpdate(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(Book book){

    }


    private int getNumRows(){
        int num = 0;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT id FROM book");
            resultSet.last();
            num = resultSet.getRow();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;

    }
    private int getNextId(){
        int nextId = 0;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT id FROM book");
            resultSet.last();
            nextId = resultSet.getInt("id") + 1;
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

    private String getAuthorName(int idAuthor){
        String authorName = " ";
        ResultSet resultSet;

        try {
            String sql = "SELECT id,name,surname FROM author where id = " + idAuthor;
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
            resultSet.first();

            authorName = resultSet.getString("name") + " " + resultSet.getString("surname");
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authorName;

    }





}
