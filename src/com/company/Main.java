package com.company;

import java.io.*;
import java.util.*;

import static java.util.Collections.replaceAll;

public class Main {

  static ArrayList<String[]> heap;
  static ArrayList<String[]> pointers;
  static ArrayList<String> roots;
  static int[] fromSpace = new int[100];
  static int[] toSpace = new int[100];


  public static void main(String[] args) {
    try {
      read_heap();
      convert_heap();
      read_pointers();
      convert_pointers();
      read_roots();
      mark_compact();
      mark_compact2();
      resize();
      write_result();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  static void read_heap() throws IOException {
    heap = new ArrayList<String[]>();
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
    pointers = new ArrayList<>();
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
    roots = new ArrayList<>();
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
  static ArrayList<element> newheap = new ArrayList<>();
  public static void convert_heap(){
    for (int i=0 ; i<heap.size() ; i++){
      String id = heap.get(i)[0];
      int start = Integer.parseInt(heap.get(i)[1]);
      int end = Integer.parseInt(heap.get(i)[2]);
      element obj = new element(id , start , end);
      newheap.add(obj);

    }
  }

  public static void convert_pointers(){
    for (int i=0 ; i<pointers.size() ; i++){
      String id = pointers.get(i)[0];
      id = id.replaceAll("\\uFEFF", "");
      for (int j=0 ; j<newheap.size(); j++){
        if (id.equals(newheap.get(j).getId())){
          newheap.get(j).addd(pointers.get(i)[1]);
        }
      }

    }
  }
  public static void mark_compact(){
    for (int i=0 ; i<roots.size() ; i++){
      String id = roots.get(i);
      for (int j=0 ; j<newheap.size(); j++){
        if (id.equals(newheap.get(j).getId())){
          newheap.get(j).flag = true;
          flag_pointers(id);
        }
      }
    }
  }
  public static void flag_pointers(String id){
    for (int i=0 ; i<newheap.size() ; i++){
      if (id.equals(newheap.get(i).getId())){
        for (int j=0 ; j< newheap.get(i).point.size() ; j++){
          String new_id =newheap.get(i).point.get(j);
          flag(new_id);

        }
      }
    }
  }
  public static void flag(String id){
    for (int i=0 ; i<newheap.size() ; i++) {
      if (id.equals(newheap.get(i).getId())){
        newheap.get(i).flag = true;
        flag_pointers(id);
      }
    }
  }

  static ArrayList<element> ans = new ArrayList<>();
  public static void mark_compact2(){
    for (int i=0 ; i<newheap.size() ; i++){
      if (newheap.get(i).flag == true){
        ans.add(newheap.get(i));
      }
    }
  }

  public static void resize(){
    int counter = 0 ;
    int size = 0;
    for (int i=0 ; i<ans.size(); i++){
      int start = ans.get(i).start;
      int end = ans.get(i).end;
      size = (end-start);
      ans.get(i).start = counter;
      ans.get(i).end = size +counter;
      counter +=size + 1;

    }
  }

  public static void write_result() throws IOException {
    FileWriter csvWriter = new FileWriter("new.csv");
    for (int i=0 ; i<ans.size(); i++){
      csvWriter.append(ans.get(i).getId()+","+ans.get(i).getStart()+","+ans.get(i).getEnd()+"\n");
    }
    csvWriter.flush();
    csvWriter.close();
  }
}
