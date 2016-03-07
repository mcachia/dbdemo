package com.mark.dbdemo.HomeWork;

import com.mark.dbdemo.Contacts;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by mcachia on 04/03/2016.
 */
public class Main {


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        CSV csvWriter = new CSV();

        Contacts contacts = new Contacts();
        contacts.setName("mark");
        contacts.setSurname("cachia");

        csvWriter.write(contacts);
    }
}
