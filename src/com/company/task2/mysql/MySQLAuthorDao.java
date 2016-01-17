package com.company.task2.mysql;

import com.company.task2.dao.AuthorDao;
import com.company.task2.entity.Author;
import com.company.task2.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ira on 12.01.2016.
 */
public class MySQLAuthorDao implements AuthorDao {
    private Connection connection;
    private  Statement statement;

    public MySQLAuthorDao(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public void save(Author author){

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int id = getNextId();
            author.setId(id);
            String sql = ("INSERT INTO author VALUES (" + id + ",'" + author.getName() + "'," + author.getSurname() + ")");
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{

        }
    }

    public Author findById(int idAuthor){
        Author author = new Author();

        try {
            String sql = "SELECT id,name,surname FROM author where id = " + idAuthor;
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.first();
            author.setId(resultSet.getInt("id"));
            author.setName(resultSet.getString("name"));
            author.setSurname(resultSet.getString("surname"));
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }
    public void update(Author author){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String sql = "update Author set name = '" + author.getName() + "', surname =" + author.getSurname() +
                    "where id =" + author.getId();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{

        }
    }

    public void delete(Author author){
        try {
            String sql = "delete FROM author where id = " + author.getId() + " and name = '" + author.getName() + "' and surname = '" + author.getSurname() + "'";
            System.out.println(sql);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println(statement.executeUpdate(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Book author){

    }

    public List<Author> findBySurname(String surname){
        List<Author> authors = new ArrayList<>();
        try {
            String sql = "SELECT id,name,surname FROM author where surname= '" + surname + "'";
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                author.setSurname(resultSet.getString("surname"));
                authors.add(author);

            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }
    public Author findByBook(Book book){
        Author author = new Author();
        try {
            String sql = "SELECT idAuthor FROM book where idAuthor= " + book.getIdAuthor();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            author.setId(resultSet.getInt("idAuthor"));
            resultSet.close();
            sql = "select name,surname from author where id=" + author.getId();
            resultSet = statement.executeQuery(sql);
            author.setName(resultSet.getString("name"));
            author.setSurname(resultSet.getString("surname"));
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    public List<Author> findAll(){
        List<Author> authors = new ArrayList<>();

        try {
            String sql = "SELECT id,name,surname FROM author";
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                author.setSurname(resultSet.getString("surname"));
                authors.add(author);

            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }

    private int getNextId(){
        int nextId = 0;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("SELECT id FROM author");
            resultSet.last();
            nextId = resultSet.getInt("id") + 1;
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }



}
