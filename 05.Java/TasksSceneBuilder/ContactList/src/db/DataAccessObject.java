/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import DataTransferObject.Contact;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.derby.jdbc.ClientDriver;

/**
 *
 * @author anas
 */
public class DataAccessObject {
    
    public static int newContact(Contact contact) throws SQLException
    {
        int  insertionStatus=0;
        /* 1. load-register driver */
        DriverManager.registerDriver(new ClientDriver());
        /* 2. make sql connection */
        Connection connection=DriverManager.getConnection("jdbc:derby://localhost:1527/ContactsList","root","root");
        /**/
        PreparedStatement statment =connection.prepareStatement("INSERT INTO contact VALUES (?,?,?,?,?)");
        statment.setInt(1,contact.getID());
        statment.setString(2,contact.getFirstName());
        statment.setString(3,contact.getLastName());
        statment.setString(4,contact.getPhoneNumber());
        statment.setString(5,contact.getEmail());


        insertionStatus=statment.executeUpdate();
        
        return insertionStatus;
    }
    
     public static int deleteContact(Contact contact) throws SQLException
    {
        int  insertionStatus=0;
        /* 1. load-register driver */
        DriverManager.registerDriver(new ClientDriver());
        /* 2. make sql connection */
        Connection connection=DriverManager.getConnection("jdbc:derby://localhost:1527/ContactsList","root","root");
        /**/
        PreparedStatement statment =connection.prepareStatement("DELETE FROM CONTACT WHERE ID='Alfreds Futterkiste';");
        statment.setInt(1,contact.getID());
        statment.setString(2,contact.getFirstName());
        statment.setString(3,contact.getLastName());
        statment.setString(4,contact.getPhoneNumber());
        statment.setString(5,contact.getEmail());


        insertionStatus=statment.executeUpdate();
        
        return insertionStatus;
    }
}
