package com.company;

import java.util.ArrayList;

public class element {
    String id;
    int start;
    int end ;
    ArrayList<String> point = new ArrayList<>();
    Boolean flag = false;

    public element(String id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public ArrayList<String> getPoint() {
        return point;
    }

    /*public void setPoint(ArrayList<String> point) {
        this.point = point;
    }*/
    public void addd(String id){
        point.add(id);
    }
}
