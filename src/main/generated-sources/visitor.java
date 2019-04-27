//import com.sqli.grammar.TestBaseVisitor;
//import com.sqli.grammar.TestParser;
import org.antlr.v4.runtime.ParserRuleContext;

public class visitor extends TestBaseVisitor {

        String[] taut=new String[30];
        int t=0;
        /*
        @Override public void visitEveryRule(ParserRuleContext ctx) {  //see gramBaseListener for allowed functions
            System.out.println("Test log overall!: " + ctx.getText());      //code that executes per rule
        }
        */
        
        @Override public String visitQuery(TestParser.QueryContext ctx) {  //see gramBaseListener for allowed functions
            return ctx.getText();//"Test log from visitor!: " + ctx.getText();      //code that executes per rule
        }
        
        @Override public String visitTautology(TestParser.TautologyContext ctx) {  //see gramBaseListener for allowed functions
            taut[t]=ctx.getText();
            t++;
            System.out.println("Found potential tautology #"+t+"!");
            return "Found potential tautology #"+t+"!";//"Test log from visitor!: " + ctx.getText();      //code that executes per rule
        }
        
        public String[] getTaut(){
            return taut;
        }
        
        public int getTautCount(){
            return t;
        }
        
        /*
        
        @Override public String visitName(TestParser.NameContext ctx) { 
            return "Found a name! " + ctx.getText();
        }
        
        @Override public String visitEmoticon(TestParser.EmoticonContext ctx) { 
            return "Here's an emoticon! " + ctx.getText();
        }
        */
        
    } 
