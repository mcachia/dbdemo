package com.mark.dbdemo;

import java.sql.*;

/**
 * Created by mcachia on 18/02/2016.
 */
public class MainApp {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");  // load DB driver

        // Get a connection to the database
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "pgadmin");

        // Create a new statement handle
        Statement statement = connection.createStatement();
        // Go tell the database to execute the following query
        ResultSet rs = statement.executeQuery("select * from mark.client");
        while (rs.next()) {
            String name = rs.getString("name"); // get the value of the column named "name"
            String phone = rs.getString("phone"); // get the value of the column named phone

            System.out.format("Name = %s, phone = %s\n", name, phone);
        }

        String name; // = get this from website eg search
//        "select * from client where name = '"+name+"'"  // SQL INJECTION ATTACK

//        name = "Wallace";
//        name = "'; DELETE from client";
//
//        select * from client where name = ''; DELETE FROM CLIENT ;


        // Same example but done with a preparedStatement before
        // Prepare a statement. Do this line once somewhere
        PreparedStatement ps = connection.prepareStatement("Select * from mark.client where name = ?");

        // Replace first ? with mark
        ps.setString(1, "Mark");

        ResultSet rs2 = ps.executeQuery();
        while (rs2.next()) {
            String phone = rs2.getString("phone");
            System.out.println(phone);
        }


        // Replace first ? with mark
        ps.setString(1, "wallace");


        rs2 = ps.executeQuery();
        while (rs2.next()) {
            String phone = rs2.getString("phone");
            System.out.println(phone);
        }

        rs2.close();
        ps.close();

        PreparedStatement ps4 = connection.prepareStatement("DELETE from mark.client where phone = ?");
        PreparedStatement ps3 = connection.prepareStatement("UPDATE mark.client SET name = ? where name = ?");
        PreparedStatement ps2 = connection.prepareStatement("SELECT * from mark.client where phone = ?");

        PreparedStatement ps5 = connection.prepareStatement("INSERT INTO mark.client(name,phone) VALUES (?, ?)");
        insertData("Wallace", "123", ps);
        insertData("Mark", "456", ps);

        ps4.close();
        ps3.close();
        ps2.close();
        ps.close();
        rs.close();
        statement.close();
        connection.close();


        // Create methods so that:
        // 1. I can insert a new name and phone
        // 2. I can search by phone
        // 3. I can search by name
        // 4. I can search by name and phone
        // 5. I give you an old name and a new name. Any records matching the old name are updated to the new one (UPDATE tablename set oldcolumn = newvalue WHERE whereclause)
    }

    public static void insertData(String name, String phone, PreparedStatement preparedStatement) throws SQLException {

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, phone);
        preparedStatement.execute();

    }

    public static ResultSet search(String phone, PreparedStatement ps2) throws SQLException {
        ps2.setString(1, phone);
        return ps2.executeQuery();

    }

    public static void update(String oldName, String newName, PreparedStatement ps3) throws SQLException {
        ps3.setString(1, newName);
        ps3.setString(2, oldName);
        ps3.execute();


    }

    public static void delete(String phone, PreparedStatement ps) throws SQLException {
        ps.setString(1, phone);
        ps.execute();

    }
}

