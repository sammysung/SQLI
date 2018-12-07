import java.util.Scanner;
import java.io.*;

public class build{
    public build(File in, File out, boolean n, boolean t, boolean v, boolean w){

        String[] query=new String[200];
        int i=0;
        Scanner key=new Scanner(System.in);
        
        FileReader file=null;
        FileWriter write=null;
        BufferedWriter b=null;
        Scanner read=null;
        String input="";
        String fin="";
        
        try{
            file=new FileReader(in);
            read=new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("The file \""+in+"\" does not exist! Please point to a proper file. Exiting...");
            System.exit(1);
        }
        read.useDelimiter("\\n|;\\n|\n");
        while(read.hasNext()){
            input=read.next();
            query[i]=input;
            fin+=query[i];
            i++;
        }
        for(int c=0; c<i; c++){
            String[] q=query[c].split(" ");
            System.out.println(q[0]);
        }
        if(!out.exists()){
            try{
                out.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(out.exists()&&w==false){
           System.out.println("Do you want to overwrite the file \""+out+"\"? Please input 'Y' for yes and anything else for no.");
           String c=key.next();
           if(!c.equalsIgnoreCase("Y")){
               System.out.println("File will not be overwritten. Exiting...");
               System.exit(0);
           }
           System.out.println("Overwriting old report...");
        }
        try{
            write=new FileWriter(out);
            b=new BufferedWriter(write);
            b.write(fin);
            b.newLine();
            b.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        for(int j=0; j<i; j++){
            System.out.println(query[j]);
        }
    }  
}
