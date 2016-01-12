package com.company.task2.entity;

/**
 * Created by Ira on 12.01.2016.
 */
public class Book {
    private int id;
    private String name;
    private int idAuthor;

    public Book() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idAuthor=" + idAuthor +
                '}';
    }

    public void init(String name, int idAuthor){
        this.name = name;
        this.idAuthor = idAuthor;
    }

    public void show(){
        System.out.println(toString());
    }
}
