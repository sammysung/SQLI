import java.util.Scanner;
import java.io.*;

/*
   Prog is currently an unused class, used to facilitate reading in static Java files and finding hotspots.
   Maybe the next group can learn from it if they want to tackle the same goal.
 */


public class prog{

    public prog(){
    
    }

    public pkg go(Scanner read, String[] query, int i){
        String[] qs=null;
        String[] back=null;
        String input="";
        String fin="";
        String hold="";
        boolean sem=false;
        boolean c=false;

        // Since this is targeting a Java SQL program, it looks for quotes (") in order to detect hotspots and begin
        // parsing. May prove useful to integrate with antlr4 code to help with web queries.

        while(read.hasNext()){
            input=read.nextLine();
            qs=input.split("\"");

            // From here on out, though, it tries to parse out the query into the best positions to recreate for
            // scanning, meaning that it may conflict with the grammar.

            if(qs.length==1)
                continue;
            else{
                for(int r=1; r<qs.length-1; r+=2){
                    c=false;
                    if(r==1&&sem==false){
                        back=qs[r].split(" ");
                        if(back[0].equalsIgnoreCase("select"))
                            sem=true;
                        else{
                            c=true;
                            break;
                        }
                    }
                    hold+=qs[r];
                }
                back=qs[qs.length-1].split(" ");
                if(back[0].equals(";"))
                    sem=false;
                if(c==false&&sem==false){
                    query[i]=hold;
                    System.out.println("Query at "+i+" is: "+query[i]);
                    fin+=query[i]+"\n";
                    i++;
                    hold="";
                }
            }
        }
        System.out.println("Prog: \n"+fin);
        pkg pkg=new pkg(query, i);
        return pkg;
    }
}
