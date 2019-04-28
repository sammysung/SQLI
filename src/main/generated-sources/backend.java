import java.util.*;
import java.io.*;

public class backend{
    list list=new list();

    // This method is used in the frame to collect the list after parsing.

    public list db(){
        return list;
    }

    // Used to print the list out, once it is made. May be currently unused.

    public void show(){
        node current=list.first;
        while(current!=null){
            current.show();
            current=current.next;
            System.out.println();
        }
    }

    public backend(File b, driver drive){
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

        launch l=new launch();
        listener lis = l.runF(b,drive);

        String[] que=lis.getQuery();

        int quec=lis.getQueryCount();

        int id=1;
        boolean t=true;
        int len=0;
        while (len < quec) {
            //System.out.println(que[len]);
            list.add(t,que[len],id);
            id++;
            len++;
        }

        // This is old manual code to parse through the safe query file; the antlr4 grammar is used now instead to allow
        // for quick implementation of safe query scanning if needed, and to keep output exactly the same even if the
        // grammar drastically changes.

        /*
        //read.useDelimiter(";\n|;");
        read.useDelimiter("\n");
        String q="";
        String c="";
        String[] qs=null;
        int id=1;
        boolean t=true;
        while(read.hasNext()){
            q=read.next();
            //qs=q.split("\n\n");
            qs=q.split("\n");
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
       */
    }
}
