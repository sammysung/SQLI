//import com.sqli.grammar.TestBaseListener;
//import com.sqli.grammar.TestParser;
import org.antlr.v4.runtime.ParserRuleContext;

public class listener extends TestBaseListener {
        String[] taut= new String[70];
        String[] tautQuery= new String[70];
        String[][] bad = new String[5][70];
        String[] query=new String[70];

        int t=0, s=0, p=0, b=0;
        int count=0;
        @Override public void enterEveryRule(ParserRuleContext ctx) {
            //see gramBaseListener for allowed functions
           // System.out.println("Test log: " + ctx.getText());      //code that executes per rule
        }
        
        @Override public void enterQuery(TestParser.QueryContext ctx) {
            //check[c]=ctx.getText();
            //c++;
           // System.out.println("Query found"+ ctx.getText());
        }
        
        @Override public void enterTautology(TestParser.TautologyContext ctx) {
          //  System.out.println("from enterTautology"+count);
            String[] h=ctx.getText().split("=");

            if(h[0].equals(h[1])){
                bad[0][b] = Integer.toString(b+1);
                bad[1][b] = Integer.toString(count);
                bad[2][b] = "Tautology";
                bad[3][b] = h[0]+"="+h[1];
                b++;
            };
        }
        
        @Override public void enterCommand(TestParser.CommandContext ctx) { 
            //System.out.println("Here's an emoticon! " + ctx.getText());
        }
        
        @Override public void enterNested(TestParser.NestedContext ctx) {
            //query[count]=ctx.getText();
            //count++;
            //System.out.println("double "+ctx.getText());
        }
        
        @Override public void enterLongline(TestParser.LonglineContext ctx) { 
            //query[count]=ctx.getText();
            //count++;
            //System.out.println("long "+ctx.getText());
        }
        
        @Override public void enterLine(TestParser.LineContext ctx) { 
            //query[count]=ctx.getText();
            //count++;
          //  System.out.println("line "+ctx.getText());
        }
        
        @Override public void enterPiggyback(TestParser.PiggybackContext ctx) {

                bad[0][b] = Integer.toString(b+1);
                bad[1][b] = Integer.toString(count);
                bad[2][b] = "Piggyback";
                bad[3][b] = ctx.getText();
                b++;

        }


    @Override public void enterStoredprocedure(TestParser.StoredprocedureContext ctx) {

            bad[0][b] = Integer.toString(b+1);
            bad[1][b] = Integer.toString(count);
            bad[2][b] = "Stored procedure";
            bad[3][b] = ctx.getText();
            b++;
        }

    @Override public void enterLinetype(TestParser.LinetypeContext ctx) {

        query[count]=ctx.getText();
        //System.out.println("Line found!\n\n"+query[count]);
        count++;
    }


    @Override public void enterUnionattack(TestParser.UnionattackContext ctx) {

           bad[0][b] = Integer.toString(b+1);
           bad[1][b] = Integer.toString(count);
           bad[2][b] = "Union";
           bad[3][b] = ctx.getText();
           b++;

        }

    @Override public void enterUnion(TestParser.UnionContext ctx) {
        //query[count]=ctx.getText();
        //count++;
    }

        public String[][] getBadQuery() {return bad; }
        public int getBadQueryCount() { return b; }

        public String[] getTaut(){
            return taut;
        }
        public int getTautCount(){
            return t;
        }

        public String[] getQuery(){
            return query;
        }
        
        public int getQueryCount(){
            return count;
        }
    } 
