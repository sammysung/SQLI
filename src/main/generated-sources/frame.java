import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.*;

/*
    Important general notes:

    frame will pass along a driver object; this object is what controls the argument functionality, and therefore must
    also be passed to ANY class where a command line argument might change a setting.

    As of this writing, the Positive Tainting test is the only implemented test, and as such, some functionality has
    been removed, such as a test selection menu. Whether it is reimplemented or not, setting the TTest to true forces
    without user input right now. The V 0.1.1 release on Github should have the test menu in it, should you need it.

    Intellij will note that many functions are not used and that some classes are unreachable; this is not true for many
    of the cases. Anything that begins with "Test" can be assumed to be safe, as the antlr4 integration with the
    pre-existing codebase was not perfect, and Intellij cannot detect the classes until compilation time.
    Other classes that are flagged as "not used" may simply not be necessary in every run of the program.
 */

public class frame{

    //driver drive=new driver();
    /* Help (along with the -q argument to end the program) will be called on some scripts after compilation is done,
       and can be used in any run of the program. This should be updated with any changes you make to the CLI options
       and, ideally, run after every compilation for the user.
     */
    public static void help(){
        System.out.println("Usage:\n");
        System.out.println("-h: Print this help message.");
        System.out.println("-f: Enable command line input file naming mode (first argument will be input filename).");
        System.out.println("-o: Enable command line output file naming mode (third argument will be output filename if -f is present, first otherwise).");
        System.out.println("-d: Enables command line safe query list definition (second argument will be safe filename)");
        System.out.println("-i: Enable user input output file naming mode (program will prompt for output filename).");
        System.out.println("-w: If an output filename is reused, overwrite without asking.");
        System.out.println("-v: Enable verbose mode.");
        System.out.println("-q: Use this to signal immediate shutdown (focused for testing purposes or help message only runs).");
        System.out.println("\nStructure: java frame [-{h,f,o,i,q,w,v} [input file] [output file name] [safe query file]]");
        System.out.println("Example: \"java frame -hfo in.txt out\" will print the help message, read from in.txt, " +
                "and output to out-safe.txt, out-review.txt, and out-bad.txt.\n");
        System.out.println("The output filename should not have any extensions provided, as the program will assign " +
                "a .txt extension during runtime.");
        System.out.println("Arguments -f, -o, and -d do not run in order of appearance; if -o is before -f, the " +
                "first argument will still be the input file.");
        System.out.print("By default, with no arguments, the program will prompt for user input to assign the input filename and auto-");
        System.out.println("generate an output filename with the scheme \"report-{UTC Local Time}-{type}.txt\".");
        System.out.println("Arguments -i and -o are mutually exclusive. The program will use the auto-generate scheme if they are used together.\n");
    }

    // Setup for the safe query file, so the list can be passed along the chain of programs.

    public static list data(File back, driver drive){
        backend b=new backend(back,drive);
        return b.db();
    }

    public static void main(String args[]){
        /*
            The list here is for the safe query list.
            arg handles the amount of input arguments passed through on this run.
            key takes all keyboard input, when it is necessary.
         */
        list list=new list();
        int arg=0;
        Scanner key=new Scanner(System.in);
        File in=null;
        File back=null;

        // This string uses a little trick found to ensure that every run of the test will output a unique file by
        // default; as every OS can use UTC time, and UTC time is very precise, why not use that to create a default
        // file name that will not every overwrite itself?

        String pq="report-"+LocalDateTime.now();

        // ... however, Windows does not like the character ':' in filenames, so it's best to just replace that.

        pq=pq.replace(':', '-');

        // This particular print statement should be treated as verbose, but the options have not been set yet.
        // As it is now, if you don't want the filename to show, comment this out.

        //System.out.println(pq);
        //File out=new File(pq);
        driver drive=new driver();
        if(args.length==0)
            System.out.println("Running in default mode.");
        // The args length limit here is based on highest expected output from using all options, but can be increased
        // without harm if needed, or even removed.
        else if(args.length>5){
            System.out.println("Too many arguments! Exiting...");
            System.exit(0);
        }
        else{

            // The argument parser will ALWAYS expect the first argument to start with a - for the options, and that all
            // options are grouped together. System.exit(1) is a clear bad signal that is used for all exit signals as
            // of now; however, any non-zero value is bad, so use unique values for each exit if needed for debugging.

            String c=args[0];
            if(c.charAt(0)!='-'){
                System.out.println("Invalid argument syntax! Please begin the argument block with the character \'-\'! Exiting...");
                System.exit(1);
            }
            // Every character position after the '-' is looked at to see if it matches the list of acceptable input
            // (should match what the help output has). Will error out if any input does not match a case.
            // Refer to driver.java if you need to see what the drive.set methods do.

            for(int p=1; p<c.length(); p++){
                char s=c.charAt(p);
                switch(s){

                    // The arg++ statement moves you to the next position in the argument array, in order to get
                    // additional input. Needs to be used if you ever need input after the options statement.

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
                        System.exit(-1);
                    case 'w': drive.setOverWrite(true);
                        break;
                    case 'd': drive.setSafeCheck(true);
                        break;
                    case 'v': drive.setVerbose(true);
                        break;
                    default:  System.out.println("Unrecognized argument passed through! Please run the -hq argument for help with using the program! Exiting...");
                        System.exit(1);
                }
            }
        }

        // This setTTest statement should only be used when Positive Tainting is the only included test.

        drive.setTTest(true);

        if(drive.getTerm()==false){
            System.out.println("Please input the name of the input file you want to use:");
            String m=key.nextLine();
            in=new File(m);
        }

        // This first term enforces the rule that, should the user pick two methods of providing an output filename,
        // that they will just use the default and be informed of such.
        // Prevents misunderstandings, and if the auto-generate method needs to be clearly acknowledged to the user,
        // this method will allow it.

        if(drive.getInteractive()==true&&drive.getOutput()==true){
            System.out.println("The arguments -i and -o are mutually exclusive! Reverting to auto-generate procedure.");
        }
        else if(drive.getInteractive()==true){
            System.out.println("Please input the name of the output file you want to use:");
            pq=key.nextLine();
        }
        else if(drive.getOutput()==true){
            arg++;
            pq=args[arg];
        }

        // This is a fix to make the safe file check happen after the options have all been parsed, in order to catch
        // the verbose tag.

        if(drive.getSafeCheck()==true){
            drive.setSafe(true);
            arg++;
            back=new File(args[arg]);
            list=data(back,drive);
        }

        // As of now, getSafe() is ALWAYS false, since the only available test is the tainting test; this is not true
        // for all tests, and the command line option must be added if one such test is added.

        else if(drive.getSafe()==false){
            drive.setSafe(true);
            System.out.println("Please input the name of the file that contains the safe queries you want to build.");
            System.out.println("WARNING: if the queries you provide here are not safe, the tests will not work " +
                    "properly. Please only use safe queries here!");
            String m=key.nextLine();
            System.out.println();
            back=new File(m);
            list=data(back,drive);
        }
        build build=new build(in, pq, drive, list);
    }
}
