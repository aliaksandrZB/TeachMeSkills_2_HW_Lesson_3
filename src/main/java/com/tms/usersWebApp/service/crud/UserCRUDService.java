package com.tms.usersWebApp.service.crud;

import com.tms.usersWebApp.service.MySQLConnector;
import com.tms.usersWebApp.service.MyWriter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserCRUDService extends CRUDService{

    private static final String SQL_ADDED_NEW_USER = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?);";
    private static final String SQL_REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";
    private static final String SQL_REMOVE_ALL_USERS = "DELETE FROM users;";
    private static final String SQL_CHANGE_USERS_NAME = "UPDATE users SET name = ? WHERE id = ?;";
    private static final String SQL_CHANGE_USERS_LASTNAME = "UPDATE users SET last_name = ? WHERE id = ?;";
    private static final String SQL_CHANGE_USERS_AGE = "UPDATE users SET age = ? WHERE id = ?;";
    private static final String SQL_SELECTION_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SQL_SELECTION_BY_ALL = "SELECT * FROM users";

    public UserCRUDService(MySQLConnector connector) {
        setConnection(connector.getConnection());
    }

    public void addedNewUser(String name, String lastname, int age, MyWriter myWriter) throws IOException {
        try (PreparedStatement prepStatement = getConnection().prepareStatement(SQL_ADDED_NEW_USER)) {
            prepStatement.setString(1, name);
            prepStatement.setString(2, lastname);
            prepStatement.setInt(3, age);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            myWriter.webPrintln("failed request");
            e.printStackTrace();
        }
        myWriter.webPrintln("User successfully added.");
    }

    public void removeUserById(int id, MyWriter myWriter) throws IOException{
        try (PreparedStatement prepStatement = getConnection().prepareStatement(SQL_REMOVE_USER_BY_ID)) {
            prepStatement.setInt(1, id);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            myWriter.webPrintln("failed request");
            e.printStackTrace();
        }
        myWriter.webPrintln("User successfully deleted.");
    }

    public void removeAllUsers(MyWriter myWriter) throws IOException{
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(SQL_REMOVE_ALL_USERS);
        } catch (SQLException e) {
            myWriter.webPrintln("failed request");
            e.printStackTrace();
        }
        myWriter.webPrintln("All users deleted successfully.");
    }

    public void changeUserData(int id, String field, String name, String lastname, int age, MyWriter myWriter) throws IOException{
        switch (field) {
            case "name":
                changeUserName(id, name, myWriter);
                break;
            case "lastname":
                changeUserLastname(id, lastname, myWriter);
                break;
            case "age":
                changeUserAge(id, age, myWriter);
                break;
        }
    }

    private void changeUserName(int id, String name, MyWriter myWriter) throws IOException{
        try (PreparedStatement prepStatement = getConnection().prepareStatement(SQL_CHANGE_USERS_NAME)) {
            prepStatement.setString(1, name);
            prepStatement.setInt(2, id);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            myWriter.webPrintln("failed request");
            e.printStackTrace();
        }
        myWriter.webPrintln("Name changed successfully.");
    }

    private void changeUserLastname(int id, String lastname, MyWriter myWriter) throws IOException{
        try (PreparedStatement prepStatement = getConnection().prepareStatement(SQL_CHANGE_USERS_LASTNAME)) {
            prepStatement.setString(1, lastname);
            prepStatement.setInt(2, id);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            myWriter.webPrintln("failed request");
            e.printStackTrace();
        }
        myWriter.webPrintln("Lastname changed successfully.");
    }

    private void changeUserAge(int id, int age, MyWriter myWriter) throws IOException{
        try (PreparedStatement prepStatement = getConnection().prepareStatement(SQL_CHANGE_USERS_AGE)) {
            prepStatement.setInt(1, age);
            prepStatement.setInt(2, id);
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            myWriter.webPrintln("failed request");
            e.printStackTrace();
        }
        myWriter.webPrintln("Age changed successfully.");
    }

    public void displayUserInformationById(int id, MyWriter myWriter) throws IOException{
        try (PreparedStatement prepStatement = getConnection().prepareStatement(SQL_SELECTION_BY_ID)) {
            prepStatement.setInt(1, id);
            ResultSet rs = prepStatement.executeQuery();

            myWriter.webPrintln(rs.getString("name") + " " +
                                rs.getString("last_name") + ", " +
                                rs.getString("age") + ".");

            rs.close();
        } catch (SQLException e) {
            myWriter.webPrintln("failed request");
            e.printStackTrace();
        }
    }

    public void displayInformationAboutAllUsers(MyWriter myWriter) throws IOException{
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECTION_BY_ALL);

            while (rs.next()) {
                myWriter.webPrintln(rs.getString("name") + " " +
                                    rs.getString("last_name") + ", " +
                                    rs.getString("age") + ".");
            }

            rs.close();
        } catch (SQLException e) {
            myWriter.webPrintln("failed request");
            e.printStackTrace();
        }
    }

}
