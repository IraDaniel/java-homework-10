package com.company.task1;

import java.sql.*;

/**
 * Created by Ira on 09.01.2016.
 */
public class DBConnection {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;


    public DBConnection(String dbName, String dbUser, String dbPass) {
        try {
            String url = "jdbc:mysql://localhost:3306/test";
            Class.forName("com.mysql.jdbc.Driver"); // регистрируем драйвер
            connection = DriverManager.getConnection(url, dbUser, dbPass); // установка соединения

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllRows() {

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT id,name,age FROM Students");
            while (resultSet.next()) {

                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
            }
            resultSet.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
    public void updateInformation(int index){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery("SELECT id,name,age FROM Students");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int newAge = age + 5;
                //   System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
                resultSet.updateInt("age", newAge);
                resultSet.updateRow();
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
            }
            resultSet.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    private int getNumRows(){
        int num = 0;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery("SELECT id FROM Students");
            while (resultSet.next()) {
              num++;
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public void insertRow(String fio, int age){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int numRow = getNumRows();
            numRow++;
            String sql = ("INSERT INTO Students VALUES (" + numRow + ",'" + fio + "'," + age + ")");
            statement.executeUpdate(sql);
            resultSet.close();
           // showAllRows();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public void dropTable(String tName){
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "DROP TABLE" + tName;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void closeConnection() {
        try {

            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    public Connection getConnection() {

        return connection;
    }
}




