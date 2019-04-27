import java.util.Scanner;
import java.io.*;

public class read{

    public read(){
    
    }
   
    public pkg go(Scanner read, String[] query, int i){
        read.useDelimiter(";\n");
        String[] qs=null;
        String input="";
        String fin="";
        while(read.hasNext()){
            input=read.next();
            qs=input.split("\n");
            input="";
            for(int r=0; r<qs.length; r++){
                if(r!=0)
                    input+=qs[r]+" ";
                else
                    input+=qs[r];
            }
            query[i]=input;
            System.out.println("Query at "+i+" is: "+query[i]);
            fin+=query[i]+"\n";
            i++;
        }
        System.out.println("Read: \n"+fin);
        pkg pkg=new pkg(query, i);
        return pkg;
    }
}
