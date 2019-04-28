import java.util.Scanner;
import java.io.*;

public class build{
    public build(File in, String pass, driver drive, list list){
        if(drive.getVerbose()==true) {
            System.out.println("This is the provided output filename.");
            System.out.println(pass+"\n\n");
        }
        /*
            Most of the important variable for this method are pre-initialized, just for ease of reading.

            key is only used for the overwrite method, should it be removed, key should go with it.

            taint as a null is vital for pre-initialization

            The value for fin should never show up as the framework is now; treat that message as a debug message if it
            appears.


        */

        Scanner key=new Scanner(System.in);
        taint taint=null;

        File safeOut=new File(pass+"-safe.txt");
        File badOut=new File(pass+"-bad.txt");
        File reviewOut=new File(pass+"-review.txt");
        FileReader file=null;
        FileWriter write=null;
        BufferedWriter b=null;
        String fin="This is a recreation of the original file, formatted, for testing.\n\n";
        String done="";
        String safe="";
        String rev="";

        // This attempt may not look useful at the moment, but it serves as a check for a missing input file while also
        // keeping the setup for manual parsing. Can be replaced with a simple "file.exists()" check
        try{
            file=new FileReader(in);
        }
        catch(FileNotFoundException e){
            System.out.println("The file \""+in+"\" does not exist! Please point to a proper file. Exiting...");
            System.exit(1);
        }

        // runF takes a file as input, and is used to scan both the safe query file and test file; the output from the
        // two associated methods are the actual list of queries in a String Array, and the length of said array.
        launch l=new launch();
        listener lis = l.runF(in, drive);
        String[] que=lis.getQuery();

        int quec=lis.getQueryCount();
        if(drive.getVerbose()==true) {
            System.out.println("\n\nThis is the count of all the queries found in the file being scanned.");
            System.out.println(quec+"\n\n");
        }

        // As of now, this test is always set to run, but the method of changing it is left in just in case future
        // maintainers happen to implement the test menu/other tests.
        if(drive.getTTest()==true){
            taint=new taint(que, quec, list);

            fin=taint.re();
            safe=taint.safe();

            if(!safeOut.exists()){
                try{
                    safeOut.createNewFile();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            else if(safeOut.exists()&&drive.getOverWrite()==false){
                System.out.println("Do you want to overwrite the file \""+safeOut+"\"? Please input 'Y' for yes and anything else for no.");
                String c=key.next();
                if(!c.equalsIgnoreCase("Y")){
                    System.out.println("File will not be overwritten. Exiting...");
                    System.exit(0);
                }
                System.out.println("Overwriting old report...");
            }
            try{
                write=new FileWriter(safeOut);
                b=new BufferedWriter(write);
                b.write(safe);
                b.newLine();
                b.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        if(drive.getVerbose()==true) {
            System.out.println("\n\nThis is the output of potentially bad queries that is passed to the runS method.");
            System.out.println(fin+"\n\n");
            System.out.println("\n\nThis is the tainting result file...\n");
        }

        // This runs the queries that don't match the safe list to see whether they explicitly attempt an attack or if
        // they need to be reviewed manually (either actually harmless or may be contextually dangerous
        // Piped into a string for printing out the results once the danger of the queries has been determined.
        done = l.runS(fin,que,drive);

        if(!badOut.exists()){
            try{
                badOut.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(badOut.exists()&&drive.getOverWrite()==false){
           System.out.println("Do you want to overwrite the file \""+badOut+"\"? Please input 'Y' for yes and anything else for no.");
           String c=key.next();
           if(!c.equalsIgnoreCase("Y")){
               System.out.println("File will not be overwritten. Exiting...");
               System.exit(0);
           }
           System.out.println("Overwriting old report...");
        }
        try{
            write=new FileWriter(badOut);
            b=new BufferedWriter(write);
            b.write(done);
            b.newLine();
            b.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        rev=l.review();
        if(!reviewOut.exists()){
            try{
                reviewOut.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(reviewOut.exists()&&drive.getOverWrite()==false){
            System.out.println("Do you want to overwrite the file \""+reviewOut+"\"? Please input 'Y' for yes and anything else for no.");
            String c=key.next();
            if(!c.equalsIgnoreCase("Y")){
                System.out.println("File will not be overwritten. Exiting...");
                System.exit(0);
            }
            System.out.println("Overwriting old report...");
        }
        try{
            write=new FileWriter(reviewOut);
            b=new BufferedWriter(write);
            b.write(rev);
            b.newLine();
            b.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }  
}
