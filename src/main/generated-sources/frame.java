import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.*;

/*
    This is a very rough work in progress build of the framework application.
    What is here as of 12/5/18 is the ability to read in file, delimit it by a 
    semicolon immediately followed by a newline, and save that both to an array
    and to a file generated with the UTC time (making it always unique).
    Plans for the future:
    Use case statement to present options, as well as allow user to run without command line args [done]
    Tie into actual scanner functionality (the way it's done now should implictily catch one type of attack, if analyzed properly)
    Cleanup?
*/

public class frame{

    public static void help(){
        System.out.println("Usage:\n");
        System.out.println("-h: Print this help message.");
        System.out.println("-f: Enable command line input file naming mode (first argument will be input filename).");
        System.out.println("-o: Enable command line output file naming mode (third argument will be output filename if -f is present, first otherwise).");
        System.out.println("-d: Enables command line safe query list definition (second argument will be safe filename)");
        System.out.println("-i: Enable user input output file naming mode (program will prompt for output filename).");
        System.out.println("-w: If an output filename is reused, overwrite without asking.");
        System.out.println("-q: Use this to signal immediate shutdown (focused for testing purposes or help message only runs).");
        System.out.println("\nStructure: java frame [-{h,f,o,i,q,w}] [input file] [output file]");
        System.out.println("Example: \"java frame -hfo in.txt out.txt\" will print the help message, read from in.txt, output to out.txt, and show a test selection menu.\n");
        System.out.println("Arguments -f and -o do not run in order of appearance; if -o is before -f, the first argument will still be the input file.");
        System.out.print("By default, with no arguments, the program will prompt for user input to assign the input filename and auto");
        System.out.println(" generate an output filename with the scheme \"report-{UTC Local Time}.txt\".");
        System.out.println("Arguments -i and -o are mutually exclusive. The program will use the autogenerate scheme if they are used together.\n");
        System.out.println("If a test is not selected in the arguments, a selection menu will be shown to the user.");
        System.out.println("Multiple test options can be selected, and they will run sequentially.\n");
    }

    public static list data(File back){
        backend b=new backend(back);
        return b.db();
    }

    public static void main(String args[]){
        list list=new list();
        int arg=0;
        Scanner key=new Scanner(System.in);
        File in=null;
        File back=null;
        String pq="report-"+LocalDateTime.now()+".txt";
        pq=pq.replace(':', '-');
        System.out.println(pq);
        File out=new File(pq);
        driver drive=new driver();
        if(args.length==0)
            System.out.println("Running in default mode.");
        else if(args.length>5){
            System.out.println("Too many arguments! Exiting...");
            System.exit(0);
        }
        else{
            String c=args[0];
            if(c.charAt(0)!='-'){
                System.out.println("Invaid argument syntax! Please begin the arguement block with the character \'-\'! Exiting...");
                System.exit(1);
            }
            for(int p=1; p<c.length(); p++){
                char s=c.charAt(p);
                switch(s){
                    case 'f': arg++;
                        drive.setTerm(true);
                        in=new File(args[arg]);
                        break;
                    case 'h': help();
                        break;
                    case 'o': drive.setOutput(true);
                        break;
                    case 'i': drive.setInteractive(true);
                        break;
                    case 'q': System.out.println("Immediate shutdown option selected! Exiting...");
                        System.exit(1);
                    case 'w': drive.setOverWrite(true);
                        break;
                    case 'd': drive.setSafe(true);
                        arg++;
                        back=new File(args[arg]);
                        list=data(back);
                        break;
                    default:  System.out.println("Unrecognized argument passed through! Please run the -hq argument for help with using the program! Exiting...");
                        System.exit(1);
                }
            }
        }
        drive.setTTest(true);
        if(drive.getTerm()==false){
            System.out.println("Please input the name of the input file you want to use:");
            String m=key.nextLine();
            in=new File(m);
        }
        if(drive.getInteractive()==true&&drive.getOutput()==true){
            System.out.println("The arguments -i and -o are mutually exclusive! Reverting to autogenerate procedure.");
        }
        else if(drive.getInteractive()==true){
            System.out.println("Please input the name of the output file you want to use:");
            String m=key.nextLine();
            out=new File(m);
        }
        else if(drive.getOutput()==true){
            arg++;
            out=new File(args[arg]);
        }
        if(drive.getSafe()==false){
            drive.setSafe(true);
            System.out.println("Please input the name of the file that contains the safe queries you want to build.");
            System.out.println("WARNING: if the queries you provide here are not safe, the tests will not work properly. Please only use safe queries here!");
            String m=key.nextLine();
            System.out.println();
            back=new File(m);
            list=data(back);
        }
        build build=new build(in, out, drive, list);
    }
}
