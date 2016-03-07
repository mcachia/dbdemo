package com.mark.dbdemo.HomeWork;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Homework
 * Created by mcachia on 04/03/2016.
 */
public class CSV {
    public void write(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();


        Set<String> headers = new HashSet<>();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String headerName = method.getName().substring(3);
            headers.add(headerName);
        }
        print(headers);

        List<String> values = new ArrayList<>();

        for (String header : headers) {
            String methodName = "get" + header;
            Method method = clazz.getMethod(methodName, null);
            String value = (String) method.invoke(object, null);
            values.add(value);
        }
        print(values);


    }


    public void print(Collection<String> printItems) {
        String lineToPrint = "";
        for (String printItem : printItems) {
            if (printItem == null) {
                printItem = "N/A";
            }
            if (lineToPrint.isEmpty()) {
                lineToPrint = printItem;
            } else {
                lineToPrint = lineToPrint + "," + printItem;
            }
        }
        System.out.println(lineToPrint);
    }


}
