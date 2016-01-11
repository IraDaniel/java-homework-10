package com.company.task2;

import java.util.ResourceBundle;

/**
 * Created by Ira on 11.01.2016.
 */
public class DBResourceManager {

    private final static DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle = ResourceBundle.getBundle("com.company.task2.db");

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
