package com.company;

import static com.company.Main.*;

public class CopyGC {
    static class Object{
      String id;
      int numberOfRefObj = 0;
      int startingIndex,endingIndex;
      Object[] references = new Object[10];
      Object(String id,String startingIndex,String endingIndex){
        this.id = id;
        this.endingIndex = Integer.getInteger(endingIndex);
        this.startingIndex = Integer.getInteger(startingIndex);
      }
    }
    int numberOfRoots = 0;
    Object[] rootsOfGraph = new Object[10];

    void buildGraph(){
      for (int i = 0; i < rootsOfGraph.length; i++) {
        buildGraphChildren(rootsOfGraph[i]);
      }
    }

    void buildGraphChildren(Object rootObject){
      for (String[] pointer : pointers) {
        if (pointer[0].equals(rootObject.id)) {
          for (String[] strings : heap) {
            if (strings[0].equals(pointer[1])) {
              // if the parent object is in the pointers list, add his child to his references list
              // of objects and use recursion to do the same for the child
              rootObject.references[rootObject.numberOfRefObj] = new Object(strings[0], strings[1],
                  strings[2]);
              buildGraphChildren(rootObject.references[rootObject.numberOfRefObj]);
              rootObject.numberOfRefObj++;
              break;
            }
          }
        }
      }
    }

    void buildRoots(){
      for (String current : roots) {
        for (String[] strings : heap) {
          if (strings[0].equals(current)) {
            rootsOfGraph[numberOfRoots++] = new Object(strings[0], strings[1],
                strings[2]);
          }
        }
      }
    }
}
