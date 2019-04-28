import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import static org.antlr.v4.runtime.CharStreams.fromFileName;
import static org.antlr.v4.runtime.CharStreams.fromString;
import java.io.*;

public class launch{
    public static void main(String[] args) {
    }

    public void launch(){

    }
    int tempCount=0;
    String doneBad="";
    String doneReview="";

    public listener runF(File f){
        CharStream cs = null;
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

        visitor visit = new visitor();


        //String t= visit.visitQuery(parser.query());
        //System.out.println(t);
    /*
    int i=0;
    while(i<29&&!"t".equals("<EOF>")){
        t=visit.visitTautology(parser.tautology());
        i++;
    }
    */
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listen,tree);
        //      String[] taut=listen.getTaut();
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~Test~~~~~~~~~~~~~~~~` \n"+taut);
        //    int ct=listen.getTautCount();
       /* int t=0;
        while(t<ct){
 //           System.out.println(taut[t]+"  "+t);
            String[] h=taut[t].split("=");
//            System.out.println(h[0]+"    "+h[1]);
            if(h[0].equals(h[1])){
//                System.out.println(" Tautology found! "+h[0]+"    "+h[1]);
            }
            t++;
        }


        */
        tempCount = listen.getQueryCount();
        //System.out.println(tempCount);
        return listen;



    /*
    CharStream c = fromString(t);
    ChatLexer lex = new ChatLexer(c);  //instantiate a lexer
    CommonTokenStream token = new CommonTokenStream(lex); //scan stream for tokens
    ChatParser parse = new ChatParser(token);
    Myvisitor visi = new Myvisitor();

    String e=visit.visitName(parse.name());
    System.out.println(e);

    t= visit.visitLine(parser.line());
    System.out.println(t);
    t= visit.visitLine(parser.line());
    System.out.println(t);
    parser = new ChatParser(tokens);
    t= visit.visitName(parser.name());
    System.out.println(t);
    parser = new ChatParser(tokens);
    t= visit.visitEmoticon(parser.emoticon());
    System.out.println(t);

    */
    }

    public String runS(String s, String[] allQueries){
        CharStream cs = fromString(s);  //load the file
        TestLexer lexer = new TestLexer(cs);  //instantiate a lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer); //scan stream for tokens
        TestParser parser = new TestParser(tokens);  //parse the tokens


        // Code for listener version
        ParseTree tree = parser.query(); // parse the content and get the tree
        listener listen=new listener();

        visitor visit = new visitor();


        //String t= visit.visitQuery(parser.query());
        //System.out.println(t);
    /*
    int i=0;
    while(i<29&&!"t".equals("<EOF>")){
        t=visit.visitTautology(parser.tautology());
        i++;
    }
    */
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listen,tree);
  /*       String[] outTaut = new String[30];
        int cot = 0;
        ct--;
        while(ct>-1){
//            System.out.println(taut[ct]+"  "+ct);
            String[] h=taut[ct].split("=");
//            System.out.println(h[0]+"    "+h[1]);
            if(h[0].equals(h[1])){
                outTaut[cot] ="Tautology|"+h[0]+"="+h[1]+"|"+ct;
                cot++;
                //System.out.println("Tautology found! "+h[0]+"    "+h[1]);

                //   System.out.println(listen.getQ);

            }
            ct--;
        }
*/

        String[] tempQ = listen.getQuery();
        String[][] baqQueries = listen.getBadQuery();
        int bqc = listen.getBadQueryCount();
        int tempP, badC, allC=0, i,j,k,revC=0;
        for (i=0; i<allQueries.length;i++){
            if (allQueries[i]!=null){
                allQueries[i]=allQueries[i].replaceAll("[\\t\\n\\r]+"," ");
                allC++;
            }
            if (tempQ[i] != null){
                tempQ[i]=tempQ[i].replaceAll("[\\t\\n\\r]+"," ");
            }
        }
        System.out.printf("   %-5s %-12s  %-17s  %-40s  %s \n", "#", "Query Number","Attack Type", "Attact in Query", "Bad Query");
        System.out.print( "--".repeat(75) +"\n");
        doneBad+= String.format("   %-5s %-12s  %-17s  %-40s  %s \n", "#", "Query Number","Attack Type", "Attact in Query", "Bad Query");
        doneBad+= "--".repeat(75) + "\n";


        for (badC = 0; badC<bqc; badC++){
            tempP = Integer.parseInt(baqQueries[1][badC]);
            baqQueries[4][badC] = tempQ[tempP-1];
            for (i=0; i<allQueries.length;i++){
                if (baqQueries[4][badC].equals(allQueries[i]) ){
                    tempP=i+1;
                    break;
                }
            }
            System.out.printf("   %-5s      %-7d  %-17s  %-40s  %s \n",
                    baqQueries[0][badC] ,tempP, baqQueries[2][badC], baqQueries[3][badC], baqQueries[4][badC] );
            doneBad+=String.format("   %-5s      %-7d  %-17s  %-40s  %s \n",
                    baqQueries[0][badC] ,tempP, baqQueries[2][badC], baqQueries[3][badC], baqQueries[4][badC] );
        }


        doneReview += String.format("   %-5s %-12s  %-17s    %s \n", "#", "Query Number","Status", "Query");
        System.out.printf("\n\n   %-5s %-12s  %-17s    %s \n", "#", "Query Number","Status", "Query");
        doneReview+= "--".repeat(75) + "\n";
        System.out.printf("--".repeat(75) + "\n");
        String reviewQ;
        for( i=0; i<30;i++) {
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
        for( i=0; i<30;i++) {
            if (tempQ[i] != null) {
                reviewQ = tempQ[i];
                //         System.out.println("checkall: "+ i + "    "+  reviewQ );
                for (k = 0; k < allC; k++) {
                    if (reviewQ.equals(allQueries[k])) {
                        doneReview += String.format("   %-5d      %-7d    %-40s  %s \n",
                                revC, k+1, "Review", reviewQ);
                        System.out.printf("   %-5d      %-7d    %-40s  %s \n",
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




    /*
    CharStream c = fromString(t);
    ChatLexer lex = new ChatLexer(c);  //instantiate a lexer
    CommonTokenStream token = new CommonTokenStream(lex); //scan stream for tokens
    ChatParser parse = new ChatParser(token);
    Myvisitor visi = new Myvisitor();
    
    String e=visit.visitName(parse.name());
    System.out.println(e);
    
    t= visit.visitLine(parser.line());
    System.out.println(t);
    t= visit.visitLine(parser.line());
    System.out.println(t);
    parser = new ChatParser(tokens);
    t= visit.visitName(parser.name());
    System.out.println(t);
    parser = new ChatParser(tokens);
    t= visit.visitEmoticon(parser.emoticon());
    System.out.println(t);
    
    */



}

