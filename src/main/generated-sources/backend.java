import java.util.*;
import java.io.*;

public class backend{
    list list=new list();
    public list db(){
        return list;
    }
    
    public void show(){
      //  System.out.println("~~~~~~~~~~~~~~~~~test from SHOW ");
        node current=list.first;
        while(current!=null){
            current.show();
            current=current.next;
            System.out.println();
        }
    }

    public backend(File b){
        FileReader file=null;
        Scanner read=null;
        try{
            file=new FileReader(b);
            read=new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("The file \""+b+"\" does not exist! Please point to a proper file. Exiting...");
            System.exit(1);
        }
        read.useDelimiter(";\n|;");
        String q="";
        String c="";
        String[] qs=null;
        int id=1;
        boolean t=true;
        while(read.hasNext()){
            q=read.next();
            qs=q.split("\n\n");
            q="";
            for(int i=0; i<qs.length; i++){
                if(i!=0)
                    q+=qs[i];
                else
                    q+=qs[i];
            }
            System.out.println(q);
            // print initial queries form safe
            list.add(t,q,id);
            q="";
            id++;
        }
    //    System.out.println("~~~~~~~~~~~~~bottom of BACK END~~~~~~~" );
       // show();
    }
}
