package tester;
import analyzercomponents.*;
import java.io.*;

public class LexicalSimulator {
    public static void main(String [] args){
        //Scanner input= new Scanner(System.in);
        //System.out.println("Enter the code snippet: ");
        //String myCode= input.nextLine();
        InputStream is; int i=30000;

        File file = new File("front.txt");

        try{
            is= new FileInputStream(file);
            Lexer lexer= new Lexer(is);
            try {
                while (i>0) {
                    lexer.scan();
                    i--;
                }
            } catch (SyntaxException|IOException |ClassCastException e){
              e.printStackTrace();
              }
         } catch (FileNotFoundException e){
            e.printStackTrace();
           }

     }
}
