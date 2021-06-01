package com.company;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

  static LinkedList<String[]> heap;
  static LinkedList<String[]> pointers;
  static LinkedList<String> roots;
  static int[] fromSpace = new int[100];
  static int[] toSpace = new int[100];


  public static void main(String[] args) {
    try {
      read_heap();
      read_pointers();
      read_roots();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  static void read_heap() throws IOException {
    heap = new LinkedList<>();
    BufferedReader csvReader = null;
    try {
      csvReader = new BufferedReader(new FileReader(
          "C:\\Users\\Dell\\Desktop\\Garbage-Collectors\\src\\com\\company\\heap.csv"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String row;
    String[] data = new String[0];
    while ((row = csvReader.readLine()) != null) {
      data = row.split(",");
      heap.add(data);
    }
    csvReader.close();
  }

  static void read_pointers() throws IOException {
    pointers = new LinkedList<>();
    BufferedReader csvReader = null;
    try {
      csvReader = new BufferedReader(new FileReader(
          "C:\\Users\\Dell\\Desktop\\Garbage-Collectors\\src\\com\\company\\pointers.csv"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String row;
    String[] data = new String[0];
    while ((row = csvReader.readLine()) != null) {
      data = row.split(",");
      pointers.add(data);
    }
    csvReader.close();
  }

  static void read_roots() throws IOException {
    roots = new LinkedList<>();
    BufferedReader csvReader = null;
    try {
      csvReader = new BufferedReader(new FileReader(
          "C:\\Users\\Dell\\Desktop\\Garbage-Collectors\\src\\com\\company\\roots.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String row;
    String[] data = new String[0];
    while ((row = csvReader.readLine()) != null) {
      roots.add(row);
    }
    csvReader.close();
  }
}
