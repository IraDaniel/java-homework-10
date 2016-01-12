package com.company.task2.connection;

/**
 * Created by Ira on 11.01.2016.
 */
public class ConnectionPoolException extends Exception{
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String message, Exception e){
        super(message, e);

    }
}
