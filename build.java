package com.sqli.framework;

import java.util.Scanner;
import java.io.*;

public class build{
    public build(File in, File out, driver drive, list list){

        String[] query=new String[200];
        int i=0;
        Scanner key=new Scanner(System.in);
        taint taint=null;
        pkg pkg=new pkg();
        prog prog=new prog();
        read re=new read();
        FileReader file=null;
        FileWriter write=null;
        BufferedWriter b=null;
        Scanner read=null;
        String input="";
        String fin="This is a recreation of the original file, formatted, for testing.\n\n";
        
        try{
            file=new FileReader(in);
            read=new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("The file \""+in+"\" does not exist! Please point to a proper file. Exiting...");
            System.exit(1);
        }
        
        String ext=in.getName();
        int la=ext.lastIndexOf(".");
        if(la==-1)
            ext="";
        else
            ext=ext.substring(la+1);
        if(ext.equalsIgnoreCase("java"))
            pkg=prog.go(read, query, i);
        else
            pkg=re.go(read, query, i);
        pkg.setList(list);
        
        if(drive.getTTest()==true){
            taint=new taint(pkg);
            fin=taint.re();
            System.out.println(fin);
        }
        
        if(!out.exists()){
            try{
                out.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(out.exists()&&drive.getOverWrite()==false){
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
        for(int j=0; j<i; j++)
            System.out.println(query[j]);
    }  
}
