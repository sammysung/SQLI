package com.sqli.framework;
import java.io.*;
import java.nio.charset.Charset;

public class LexicalSimulator {
    public LexicalSimulator(){
    
    }
    
    public driver run(String file, driver drive){
        //Scanner input= new Scanner(System.in);
        //System.out.println("Enter the code snippet: ");
        //String myCode= input.nextLine();
        InputStream is; int i=30000;

        //File file = new File("front.txt");

        
        
        //try{
            is = new ByteArrayInputStream(file.getBytes(Charset.forName("UTF-8")));
            //is= new FileInputStream(file);
            Lexer lexer= new Lexer(is, drive);
            
            
            try {
                while (i>0) {
                    drive=lexer.scan();
                    i--;
                }
            } catch (SyntaxException|IOException |ClassCastException e){
              e.printStackTrace();
              }
         //} 
         /*
         catch (IOException e){
            e.printStackTrace();
           }
           */
        return drive;
     }
}
