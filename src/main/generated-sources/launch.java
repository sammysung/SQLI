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
    String doneF="";

    public void runF(File f){
        CharStream cs = null;
        try{
            cs = fromFileName(f.getName());  //load the file
            doneF += "Test file: "+ f.getName()+"\n\n";

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
        String[] taut=listen.getTaut();
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~Test~~~~~~~~~~~~~~~~` \n"+taut);
        int ct=listen.getTautCount();
        int t=0;
        while(t<ct){
 //           System.out.println(taut[t]+"  "+t);
            String[] h=taut[t].split("=");
//            System.out.println(h[0]+"    "+h[1]);
            if(h[0].equals(h[1])){
//                System.out.println(" Tautology found! "+h[0]+"    "+h[1]);
            }
            t++;
        }
        tempCount = listen.getQueryCount();


    
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
    
    public String runS(String s){
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
        int tempP;
        System.out.printf("   %-5s %-12s  %-17s  %-25s  %s \n", "#", "Query Number","Attack Type", "Attact in Query", "Bad Query");
        System.out.print( "--".repeat(75) +"\n");
        doneF+= String.format("   %-5s %-12s  %-17s  %-25s  %s \n", "#", "Query Number","Attack Type", "Attact in Query", "Bad Query");
        doneF+= "--".repeat(75) + "\n";
        for (int pq = 0; pq<bqc; pq++){
                tempP = Integer.parseInt(baqQueries[1][pq]) - tempCount;
                baqQueries[4][pq] = tempQ[tempP-1].replaceAll("[\\t\\n\\r]+"," ");
                System.out.printf("   %-5s      %-7d  %-17s  %-25s  %s \n",
                        baqQueries[0][pq] ,tempP, baqQueries[2][pq], baqQueries[3][pq], baqQueries[4][pq] );
                doneF+=String.format("   %-5s      %-7d  %-17s  %-25s  %s \n",
                        baqQueries[0][pq] ,tempP, baqQueries[2][pq], baqQueries[3][pq], baqQueries[4][pq] );
            }

        return doneF;

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

