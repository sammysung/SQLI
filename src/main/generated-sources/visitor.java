import org.antlr.v4.runtime.ParserRuleContext;

/*
    This class is not used right now, but left in a semi-usable state in order to give a jumping off point for the next
    group to look at this code. Do NOT remove the import statement for the antlr4 ParserRuleContext, just to avoid any
    issues.
 */

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
        
        public int getTautCount() {
            return t;
        }
    } 
