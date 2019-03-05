package com.sqli.framework;

import java.util.*;

public class list{
    public node first;
    private static boolean safe;
    private static String query;
    private static int id;
    private int index;
    private Scanner key=new Scanner(System.in);
    
    public list(){
        first=null;
    }
    
    public node getHead(){
        return first;
    }
    
    public void add(boolean s, String q, int i){
        node l=new node(s,q,i);
        l.next=first;
        first=l;
    }
    
    public void show(){
        node current=first;
        while(current!=null){
            current.show();
            current=current.next;
            System.out.println();
        }
    }

    
    /*
    public addMid(){
        System.out.println("Enter required index:");
        index=key.nextInt();
        node new=new node(s,q,i);
        if(first
    }
    */
}
