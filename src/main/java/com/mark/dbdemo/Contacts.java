package com.mark.dbdemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by mcachia on 29/02/2016.
 */
public class Contacts {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {


        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "pgadmin");

        FileReader reader = new FileReader("C:\\Users\\mcachia\\Desktop\\Contacts.csv");
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        bufferedReader.readLine();// skip first line
        while ((line = bufferedReader.readLine()) != null) {
            String[] lineValues = line.split(",");
            String name = lineValues[0];
            String surname = lineValues[1];
            String phone = lineValues[2];
            int convert = Integer.parseInt(phone); // had to do some research for this parseInt

            PreparedStatement ps = connection.prepareStatement("INSERT INTO contacts.contact(name,surname,phone) VALUES (?, ?,?)");
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setInt(3, convert);
            ps.execute();

        }
        reader.close();


    }
}
