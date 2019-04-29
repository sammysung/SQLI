import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import static org.antlr.v4.runtime.CharStreams.fromFileName;
import static org.antlr.v4.runtime.CharStreams.fromString;
import java.io.*;

/*
    Major note:

    Intellij will highlight all references to antlr4 code, or code dependent on it, due to the way integration works
    with Maven. Essentially, the framework code has no way of seeing those classes until the jar is compiled, and so it
    can't trust that those references will actually work. These errors (usually linked to listener.java or Test* classes)
    are safe to ignore.

    Also, some older versions on the repo may have code for using the visitor methods or directly interacting with
    listener methods; in the interest of readability, they are being removed from the final review.
 */

public class launch{
    public static void main(String[] args) {
    }

    /*
        Methods runF and runS are not drastically different when it comes to using the listeners to perform tasks, but
        they are very different in scope and what is done with that information.

        As of now, runF is used to run the listener code through the safe query file and the input query file; through
        this, information on potential attacks is implicitly collected on both. However, tainting methodology presumes
        that the safe query list is, well, safe; the solution to this problem is to run a different safe query file
        against the found queries, or extend functionality beyond the exact scope of the test method.

        runS, on the other hand, is made to analyze and output results of found bad queries, as well as ones that don't
        fit a rule, but also aren't marked as safe. This will take a String input, formatted to match the input file.
     */

    public void launch(){

    }
    // tempCount is the counter of how many queries the listener has found, check output with setVerbose() or by using
    // custom printout statements.

    int tempCount=0;

    // doneBad will become a list of all marked bad queries, while doneReview lists all queries that were not marked as
    // explicitly good or bad. These are formatted to be ready to write out to a file.

    String doneBad="";
    String doneReview="";

    public listener runF(File f, driver drive){
        CharStream cs = null;

        // Using an antlr4 method to setup the file for parsing and creating a file

        try{
            cs = fromFileName(f.getName());  //load the file
            doneBad += "Test file: "+ f.getName()+"\n\n";
            doneReview += "Test file: "+ f.getName()+"\n\n";

        }
        catch (IOException e){
            e.printStackTrace();
        }
        TestLexer lexer = new TestLexer(cs);  //instantiate a lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); //scan stream for tokens
        TestParser parser = new TestParser(tokens);  //parse the tokens


        // Code for listener version

        ParseTree tree = parser.query(); // parse the content and get the tree
        listener listen=new listener();

        // The visitor initialization shown here can help you get started if you need the features they offer.

        //visitor visit = new visitor();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listen,tree);

        tempCount = listen.getQueryCount();

        if(drive.getVerbose()==true) {
            System.out.println("\n\nThis is the count of queries found in this run of the listener method.");
            System.out.println(tempCount+"\n\n");
        }
        return listen;
    }



    

    public String runS(String s, String[] allQueries, driver drive){
        CharStream cs = fromString(s);  //load the file
        TestLexer lexer = new TestLexer(cs);  //instantiate a lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); //scan stream for tokens
        TestParser parser = new TestParser(tokens);  //parse the tokens


        // Code for listener version
        ParseTree tree = parser.query(); // parse the content and get the tree
        listener listen=new listener();

        // The visitor initialization shown here can help you get started if you need the features they offer.
        //visitor visit = new visitor();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listen,tree);

        String[] tempQ = listen.getQuery();
        String[][] baqQueries = listen.getBadQuery();
        String[] tempQuery;
        String[] tempAttack;

        int bqc = listen.getBadQueryCount();
        int tempP, badC, allC=0, i,j,k,revC=0;
        for (i=0; i<allQueries.length;i++){
            if (allQueries[i]!=null){
                allQueries[i]=allQueries[i].replaceAll("[\t\n\r]+"," ");
                allC++;
            }
            if (tempQ[i] != null){
                tempQ[i]=tempQ[i].replaceAll("[\t\n\r]+"," ");
            }
        }
        System.out.printf(" %-3s %-12s   %-20s    %-40s  %s \n", "#", "Query Number","Attack Type", "Attack in Query", "Bad Query");
        System.out.print( "--".repeat(85) +"\n");
        doneBad+= String.format(" %-3s %-12s   %-20s    %-40s  %s \n", "#", "Query Number","Attack Type", "Attack in Query", "Bad Query");
        doneBad+= "--".repeat(85) + "\n";


        for (badC = 0; badC<bqc; badC++) {
            tempP = Integer.parseInt(baqQueries[1][badC]);
            baqQueries[4][badC] = tempQ[tempP - 1];
            for (i = 0; i < allQueries.length; i++) {
                if (baqQueries[4][badC].equals(allQueries[i])) {
                    tempP = i + 1;
                    break;
                }
            }
            if (baqQueries[3][badC].length() > 40 && baqQueries[4][badC].length() > 90) {
                tempAttack = baqQueries[3][badC].split("SELECT",2);
                if (tempAttack[1].length() <35) {
                    tempAttack[1] = "SELECT" + tempAttack[1];
                }
                else{
                    tempAttack[0] =  tempAttack[0]+"SELECT" ;
                }


                tempQuery = baqQueries[4][badC].split("SELECT",3);
                tempQuery[1] = "SELECT" + tempQuery[1];
                tempQuery[2] = "SELECT" + tempQuery[2];
                System.out.printf(" %-3s    %-10d %-20s    %-40s  %s \n \t\t\t\t\t\t\t\t\t\t    %-40s   %s \n",
                        baqQueries[0][badC], tempP, baqQueries[2][badC], tempAttack[0], tempQuery[1],
                        tempAttack[1], tempQuery[2]);
                doneBad +=String.format(" %-3s    %-10d %-20s    %-40s  %s \n \t\t\t\t\t\t\t\t\t\t    %-40s   %s \n",
                        baqQueries[0][badC], tempP, baqQueries[2][badC], tempAttack[0], tempQuery[1],
                        tempAttack[1], tempQuery[2]);


            } else if (baqQueries[3][badC].length() > 40) {
          //      System.out.println("if else 1");
                tempAttack = baqQueries[3][badC].split("SELECT",2);
                if (tempAttack.length ==1 ){
                    tempAttack = tempAttack[0].split("=",2);
                    tempAttack[0] = tempAttack[0] + "=";
                }
                else if (tempAttack[1].length() <35) {
                    tempAttack[1] = "SELECT" + tempAttack[1];
                }
                else{
                    tempAttack[0] =  tempAttack[0]+"SELECT" ;
                }
                System.out.printf(" %-3s    %-10d %-20s    %-40s  %s  \n  \t\t\t\t\t\t\t\t\t\t\t%-40s  \n",
                        baqQueries[0][badC], tempP, baqQueries[2][badC], tempAttack[0], baqQueries[4][badC],
                        tempAttack[1].replaceAll("[\t\n\r]+"," "));
                doneBad +=String.format(" %-3s    %-10d %-20s    %-40s  %s  \n  \t\t\t\t\t\t\t\t\t\t\t%-40s  \n",
                        baqQueries[0][badC], tempP, baqQueries[2][badC], tempAttack[0], baqQueries[4][badC],
                        tempAttack[1].replaceAll("[\t\n\r]+"," "));


            }
            else if (baqQueries[4][badC].length() > 90) {
                tempQuery = baqQueries[4][badC].split("SELECT",3);
                tempQuery[1] = "SELECT" + tempQuery[1];
                tempQuery[2] = "SELECT" + tempQuery[2];


                System.out.printf(" %-3s    %-10d %-20s    %-40s  %s  \n \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s \n",
                        baqQueries[0][badC], tempP, baqQueries[2][badC], baqQueries[3][badC].replaceAll("[\t\n\r]+"," "),
                        tempQuery[1], tempQuery[2].replaceAll("[\t\n\r]+"," "));
                doneBad +=String.format(" %-3s    %-10d %-20s    %-40s  %s  \n \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s \n",
                        baqQueries[0][badC], tempP, baqQueries[2][badC], baqQueries[3][badC].replaceAll("[\t\n\r]+"," "),
                        tempQuery[1], tempQuery[2].replaceAll("[\t\n\r]+"," "));
            }
            else {
          //      System.out.println("else");
                System.out.printf(" %-3s    %-10d %-20s    %-40s  %s \n",
                        baqQueries[0][badC], tempP, baqQueries[2][badC], baqQueries[3][badC], baqQueries[4][badC]);
                doneBad += String.format(" %-3s    %-10d %-20s    %-40s  %s \n",
                        baqQueries[0][badC], tempP, baqQueries[2][badC], baqQueries[3][badC], baqQueries[4][badC]);
            }

        }

        doneReview += String.format(" %-3s %-12s   %-20s    %s \n", "#", "Query Number","Status", "Query");
        System.out.printf("\n\n %-3s %-12s   %-20s    %s \n", "#", "Query Number","Status", "Query");
        doneReview+= "--".repeat(85) + "\n";
        System.out.printf("--".repeat(85) + "\n");
        String reviewQ;
        for( i=0; i<70;i++) {
            if (tempQ[i] != null) {
                reviewQ = tempQ[i];
                for (j = 0; j < badC; j++) {
                    if (reviewQ.equals(baqQueries[4][j])) {
                        //                    System.out.println("checkBad: "+ i + "    "+  reviewQ );

                        tempQ[i] = null;
                    }
                }
            }
        }
        for( i=0; i<70;i++) {
            if (tempQ[i] != null) {
                reviewQ = tempQ[i];
                //         System.out.println("checkall: "+ i + "    "+  reviewQ );
                for (k = 0; k < allC; k++) {
                    if (reviewQ.equals(allQueries[k])) {
                        doneReview += String.format(" %-3s   %-8s     %-20s    %s \n",
                                revC, k+1, "Review", reviewQ);
                        System.out.printf(" %-3s   %-8s     %-20s    %s \n",
                                revC+1, k+1, "Review", reviewQ);
                        revC++;
                        //break;
                    }
                }
            }
        }
//        System.out.println(doneReview);
        return doneBad;
    }
    public String review(){
        return doneReview;
    }
}

