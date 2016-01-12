package com.company.task2.entity;

/**
 * Created by Ira on 12.01.2016.
 */
public class Author {

    private int id;
    private String name;
    private String surname;

    public Author() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public void show(){
        System.out.println(toString());
    }
}
