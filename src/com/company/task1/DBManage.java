package com.company.task1;

/**
 * Created by Ira on 18.01.2016.
 */
public class DBManage {

    public static void main(String[] args){
        DBConnection dbConnection = new DBConnection("test","root","JustROOT");
        dbConnection.showAllRows();
        dbConnection.insertRow("Ivanov",20);
        dbConnection.closeConnection();

    }
}
