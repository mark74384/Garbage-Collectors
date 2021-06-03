package com.company;

import static com.company.Main.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class CopyGC {

    // an Object class to represent objects in the heap
    static class Object{
        String id;
        int numberOfRefObj = 0;
        int startingIndex = 0,endingIndex = 0;
        Object[] references = new Object[10];
        Object(String id,String startingIndex,String endingIndex){
            this.id = id;
            this.endingIndex = Integer.parseInt(endingIndex);
            this.startingIndex = Integer.parseInt(startingIndex);
        }
    }

    //root Objects
    int numberOfRoots = 0;
    Object[] rootsOfGraph = new Object[10];

    //heap memory represented using a map
    Map<String, Object> fromSpace = new HashMap<>();
    Map<String, Object> toSpace = new HashMap<>();
    int c = 0; //index for next heap memory insertion

    // BFS Queue
    Queue<Object> objectQueue = new LinkedList<>();

    // using given roots builds the graph using given pointers
    void buildGraph(){
        for (int i = 0; i < numberOfRoots; i++) {
            buildGraphChildren(rootsOfGraph[i]);
        }
    }


    // when an object in the pointers list is traversed,searches for it in the heap list and
    // adds it to the graph
    void buildGraphChildren(Object rootObject){
        for (String[] pointer : pointers) {
            if (Integer.parseInt(pointer[0]) == Integer.parseInt(rootObject.id)) {
                for (String[] strings : heap) {
                    // if the parent object is in the pointers list, add his child to his references list
                    // of objects and use recursion to do the same for the child
                    if (Integer.parseInt(pointer[1]) == Integer.parseInt(strings[0])) {
                        // if the child object is already allocated (present in the hashMap), add it to the
                        // graph, else, allocate it  and add to the graph
                        if(!fromSpace.containsKey(strings[0])){
                            Object newObj = new Object(strings[0], strings[1],
                                    strings[2]);
                            rootObject.references[rootObject.numberOfRefObj] = newObj;
                            fromSpace.put(newObj.id,newObj);
                            buildGraphChildren(newObj);
                        } else {
                            rootObject.references[rootObject.numberOfRefObj] = fromSpace.get(strings[0]);
                        }
                        rootObject.numberOfRefObj++;
                        break;
                    }
                }
            }
        }
    }

    // extracts the roots (currently referenced objects) from the list
    void buildRoots(){
        for (String current : roots) {
            for (String[] strings : heap) {
                if (strings[0].equals(current)) {

                    rootsOfGraph[numberOfRoots] = new Object(strings[0], strings[1],
                            strings[2]);
                    fromSpace.put(rootsOfGraph[numberOfRoots].id,rootsOfGraph[numberOfRoots]);
                    numberOfRoots++;
                }
            }
        }
    }

    void copyAlgo(String path) throws IOException {
        for (int i = 0; i < numberOfRoots; i++) {
            objectQueue.add(rootsOfGraph[i]);
        }
        FileWriter csvWriter = new FileWriter(path);
        while (!objectQueue.isEmpty()){
            Object current = objectQueue.poll();


            if(!toSpace.containsKey(current.id)){
                csvWriter.append(current.id);
                csvWriter.append(",");
                int size = current.endingIndex - current.startingIndex + 1;
                current.startingIndex = c;
                csvWriter.append(Integer.toString(c));
                csvWriter.append(",");
                c = c + size;
                current.endingIndex = c-1;
                csvWriter.append(Integer.toString(c-1));
                csvWriter.append("\n");
                toSpace.put(current.id,current);
            }

            for (int i = 0; i < current.numberOfRefObj; i++) {
                objectQueue.add(current.references[i]);
            }
        }
        csvWriter.flush();
        csvWriter.close();

        fromSpace = toSpace;
        toSpace.clear();
        c = 0;
    }

    void traverseGraph(Object rootObject){

    }
}
