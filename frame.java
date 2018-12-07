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
        System.out.println("-a: Run all tests implemented sequentially. Order to be updated as implementation is completed.");
        System.out.println("-n: Run AMNESIA test only. End once queries are scanned and analyzed. (Not yet implemented)");
        System.out.println("-t: Run Positive Tainting tests only. End once queries are scanned and analyzed. (In progress)");
        System.out.println("-f: Enable command line input file naming mode (first argument will be input filename).");
        System.out.println("-o: Enable command line output file naming mode (second argument will be output filename if -f is present, first otherwise).");
        System.out.println("-i: Enable user input output file naming mode (program will prompt for output filename).");
        System.out.println("-v: Enable verbose mode (as of now, is just a stub of functionality).");
        System.out.println("-w: If an output filename is reused, overwrite without asking.");
        System.out.println("-q: Use this to signal immediate shutdown (focused for testing purposes or help message only runs).");
        System.out.println("\nStructure: java frame [-{h,f,o,v,i,q,w,a,n,t}] [input file] [output file]");
        System.out.println("Example: \"java frame -hfov in.txt out.txt\" will print the help message, read from in.txt, output to out.txt, show a test selection menu, and be verbose.\n");
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
    
        boolean f=false;
        boolean o=false;
        boolean v=false;
        boolean i=false;
        boolean n=false;
        boolean t=false;
        boolean w=false;
        boolean u=false;
        boolean d=false;
        int arg=0;
        Scanner key=new Scanner(System.in);
        File in=null;
        File back=null;
        File out=new File("report-"+LocalDateTime.now()+".txt");
        if(args.length==0)
            System.out.println("Running in default mode.");
        else if(args.length>4){
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
                              f=true;
                              in=new File(args[arg]);
                              break;
                    case 'v': v=true;
                              System.out.println("Running in verbose mode now.");
                              break;
                    case 'h': help();
                              break;
                    case 'o': o=true;
                              break;
                    case 'i': i=true;
                              break;
                    case 'q': System.out.println("Immediate shutdown option selected! Exiting...");
                              System.exit(1);
                    case 'w': w=true;
                              break;
                    case 'a': n=true;
                              t=true;
                              u=true;
                              break;
                    case 'n': n=true;
                              u=true;
                              break;
                    case 't': t=true;
                              u=true;
                              break;
                    case 'd': d=true;
                              break;
                    case 'D': arg++;
                              back=new File(args[arg]);
                              list=data(back);
                              break;
                    default:  System.out.println("Unrecognized argument passed through! Please run the -hq argument for help with using the program! Exiting...");
                              System.exit(1);
                }
            }
        }
        if(d==true){
            System.out.println("Please input the name of the file that contains the safe queries you want to build.");
            System.out.println("WARNING: if the queries you provide here are not safe, the tests will not work properly. Please only use safe queries here!");
            String m=key.nextLine();
            System.out.println();
            back=new File(m);
            list=data(back);
        }
        if(f==false){
            System.out.println("Please input the name of the input file you want to use:");
            String m=key.nextLine();
            in=new File(m);
        }
        if(i==true&&o==true){
            System.out.println("The arguments -i and -o are mutually exclusive! Reverting to autogenerate procedure.");
        }
        else if(i==true){
            System.out.println("Please input the name of the output file you want to use:");
            String m=key.nextLine();
            out=new File(m);
        }
        else if(o==true){
            arg++;
            out=new File(args[arg]);
        }
        if(u==false){
            System.out.println("Please indicate the tests you would like to run, and type the corrosponding letter(s), followed by the enter key:");
            System.out.println("Options:\n");
            System.out.println("AMNESIA: n");
            System.out.println("Positive Tainting: t");
            System.out.println("All Tests: a");
            String sel=key.nextLine();
            for(int l=0; l<sel.length(); l++){
                char se=sel.charAt(l);
                if(se=='a'){
                    n=true;
                    t=true;
                    break;
                }
                else if(se=='n')
                    n=true;
                else if(se=='t')
                    t=true;
                else
                    System.out.println("\""+se+"\" is not a recognized entry in the test table!");
            }
        }
        build build=new build(in, out, n, t, v, w, list);
    }
}
