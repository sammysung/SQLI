import java.util.Scanner;
import java.io.*;

public class build{
    public build(File in, File out, driver drive, list list){
      //  System.out.println(out.getName());
     //   static String[] reportData = new String[200];
        String[] query=new String[200];
        int i=0;
        Scanner key=new Scanner(System.in);
        taint taint=null;
        //pkg pkg=new pkg();
        //prog prog=new prog();
        //read re=new read();
        FileReader file=null;
        FileWriter write=null;
        BufferedWriter b=null;
        Scanner read=null;
        String input="";
        String fin="This is a recreation of the original file, formatted, for testing.\n\n";
        String done="";
        
        try{
            file=new FileReader(in);
            read=new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("The file \""+in+"\" does not exist! Please point to a proper file. Exiting...");
            System.exit(1);
        }
        /*
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
        
        query=pkg.getArray();
        int y=pkg.getCount()-1;
        /*
        System.out.println(y);
        for(y=y; y>-1; y--){
            System.out.println("Out of the lex: "+query[y]);
            lex.run(query[y]);
        }
        
        */

        launch l=new launch();
        listener lis = l.runF(in);

        //listener lis=new listener();
        
        String[] que=lis.getQuery();

        int quec=lis.getQueryCount();

        //System.out.println(quec);
        
        if(drive.getTTest()==true){
            taint=new taint(que, quec, list);

            fin=taint.re();
        }
        //System.out.println(fin);

        System.out.println("\n\nThis is the tainting result file...\n");
        done = l.runS(fin,que );

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
            b.write(done);
            b.newLine();
            b.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }  
}
