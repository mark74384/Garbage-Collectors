package com.company;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args){
        try {

            LinkedList<String[]> list = new LinkedList<>();
            BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\Dell\\Desktop\\lab2\\src\\com\\company\\heap.csv"));
            String row;
            String[] data = new String[0];
            while ((row = csvReader.readLine()) != null) {
                data = row.split(",");
                list.add(data);
            }
            System.out.println(Arrays.toString(list.get(1)));
            String [] arr = list.get(1);
            System.out.println(arr[0]);
            csvReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("hgfhfhdydtu");


    }
}
