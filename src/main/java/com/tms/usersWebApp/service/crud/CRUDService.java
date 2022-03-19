package com.tms.usersWebApp.service.crud;

import com.tms.usersWebApp.service.MyWriter;

import java.sql.Connection;
import java.sql.SQLException;
public abstract class CRUDService {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            MyWriter.println("failed to close connector");
            e.printStackTrace();
        }
    }

}
