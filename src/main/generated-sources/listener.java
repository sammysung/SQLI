import org.antlr.v4.runtime.ParserRuleContext;

/*
    This is the class that actually integrates the antlr4 grammar with the framework, as all the @Override methods
    listed here are also listed in the TestBaseListener.java file. Some basic commented code has been left in the
    methods not used just to show possible uses.

    The 70 size limit in queryLimit is arbitrary, change it as needed.
 */

public class listener extends TestBaseListener {
        int queryLimit=70;

        // The methods using taut or tautQuery are used for just detecting tautology attacks, if that feature is needed
        // in the future. They are not used right now.

        //String[] taut= new String[queryLimit];
        //String[] tautQuery= new String[queryLimit];
        String[][] bad = new String[5][queryLimit];
        String[] query=new String[queryLimit];
        String[] storedP = {"CREATE","INSERT","DELETE","SHUTDOWN","UPDATE","DROP"};


    int t=0, s=0, p=0, b=0;
        int count=0;

        public boolean isInteger(String s){
            return s.matches("\\d+");
        }

    class STR
    {
        String nums[];
        String ops[];
        char op[];
        int num[];
        int index;
        int limit;
        boolean check;


        // This class simply checks a string against the regex expression for all digits; ergo, if it
        // matches, you have an integer, and if it doesn't, you have anything else.
        // Test if this catches things like "Dept5" or anything like that.

        public boolean isInteger(String s){
            return s.matches("\\d+");
        }


        // This class will convert only the left side of an equation into a sum, and currently only works with
        // addition. You must pass a whole equation to the class; "1+1+1=3" will work, while "1+1+1" will not!

        public STR(String str, String delim)
        {
            limit = str.length();

            nums = new String[limit];
            num = new int[limit];
            ops = new String[limit];
            op  = new char[limit];
            check=false;

            String    token, op;
            char      c1,c2;
            index     =  0;
            int start =  0;
            int end   = -1;

            for(int i = 0; i < str.length()-1; i++)
            {
                c1 = str.charAt(i);
                c2 = str.charAt(i+1);

                if(isDelim(c1, delim) &&! isDelim(c2, delim))
                {
                    start = i+1;
                }

                if(isDelim(c2, delim) &&! isDelim(c1, delim))
                {
                    end = i+1;
                }

                if (start < end) {

                    token = str.substring(start, end);
                    op = str.substring(end, end+1);
                    nums[index] = token;
                    ops[index] = op;
                    index++;
                    start = i+1;
                }
            }

            if(start > end)
            {
                token = str.substring(start);
                nums[index] = str.substring(start);
                index++;
            }
        }

        public boolean isDelim(char c, String s)
        {
            for(int i = 0; i < s.length(); i++)
            {
                if(c == s.charAt(i))
                {
                    return true;
                }
            }
            return false;
        }

        public void convert(){
            for(int i = 0; i < nums.length; i++)
            {
                // This flag here will prevent non-integer input from being passed to parseInt, and will
                // also flag the equation further down to let it know that a variable name was found.
                if(!isInteger(nums[i])){
                    check=true;
                    break;
                }
                if(nums[i] == null) break;
                num[i] = Integer.parseInt(nums[i]);
                if(ops[i].equals("+")) {
                    op[i] = '+';
                }
                if(ops[i].equals("-")){
                    op[i] = '-';
                }
                if(ops[i].equals("=")) break;
            }
        }

        public int math(){
            int sum = 0;

            for(int i = 0; i < num.length; i++){

                if(op[i] == '+')
                {
                    sum = sum + num[i];
                    continue;
                }
                if(op[i] == '-'){
                    sum = sum - num[i];
                    continue;
                }
                sum = sum + num[i];
            }

            return sum;
        }

        public boolean flagged(){
            return check;
        }
    }


        @Override public void enterEveryRule(ParserRuleContext ctx) {
            //see gramBaseListener for allowed functions
           // System.out.println("Test log: " + ctx.getText());      //code that executes per rule
        }

        @Override public void enterQuery(TestParser.QueryContext ctx) {
            //check[c]=ctx.getText();
            //c++;
           // System.out.println("Query found"+ ctx.getText());
        }

    @Override public void enterIsequal(TestParser.IsequalContext ctx) {
            //  System.out.println("from enterTautology"+count);

            // Tautology attacks require this framework to calculate whether the right and left sides are truly equals,
            // at least for the purpose of seeing if this is usable attack. OR statements are not, by default, bad.

            String[] h=ctx.getText().split("=");

            if(!isInteger(h[0])&&!isInteger(h[1])){
                if(h[0].trim().equals(h[1].trim())){
                    bad[0][b] = Integer.toString(b+1);
                    bad[1][b] = Integer.toString(count);
                    bad[2][b] = "Tautology";
                    bad[3][b] = h[0]+"="+h[1];
                    b++;
                }
                return;
            }

            //System.out.println(ctx.getText());

            STR stok= new STR(ctx.getText(),"+-=");
            stok.convert();
            int left = stok.math();
            int right=0;
            if(!isInteger(h[1])){
                right=-10;
            }
            else{
                right=Integer.parseInt(h[1]);
            }

            boolean c=stok.flagged();
            if(right!=-10||!c){
                if(left==right){
                    bad[0][b] = Integer.toString(b+1);
                    bad[1][b] = Integer.toString(count);
                    bad[2][b] = "Tautology";
                    bad[3][b] = h[0]+"="+h[1];
                    b++;
                }
            }
            else {
                if(h[0].trim().equals(h[1].trim())){
                    bad[0][b] = Integer.toString(b+1);
                    bad[1][b] = Integer.toString(count);
                    bad[2][b] = "Tautology";
                    bad[3][b] = h[0]+"="+h[1];
                    b++;
                }
            }
        }

        // Just due to the fact that we need both the left and right side of equations, the following rule
        // is not useful as of now to correctly ID tautology attacks.


        /*
    @Override public void enterPlusdigit(TestParser.PlusdigitContext ctx) {
        System.out.println(ctx.getText());
        STR stok= new STR(ctx.getText()+"=0","+-=");
        stok.convert();
        int left = stok.math();
        int right=Integer.parseInt(h[1]);
        if(left==right){
            bad[0][b] = Integer.toString(b+1);
            bad[1][b] = Integer.toString(count);
            bad[2][b] = "Tautology";
            bad[3][b] = h[0]+"="+h[1];
            b++;
        }
    }
    */



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
                bad[3][b] = ctx.getText().trim();
                b++;

                for (s=0; s<storedP.length; s++){
                    if(ctx.getText().toUpperCase().contains(storedP[s])){
                        bad[0][b] = Integer.toString(b+1);
                        bad[1][b] = Integer.toString(count);
                        bad[2][b] = "Stored procedure";
                        bad[3][b] = storedP[s];
                        b++;
                    }
                }

        }

    /*
    @Override public void enterStoredprocedure(TestParser.StoredprocedureContext ctx) {

            bad[0][b] = Integer.toString(b+1);
            bad[1][b] = Integer.toString(count);
            bad[2][b] = "Stored procedure";
            bad[3][b] = ctx.getText();
            b++;
        }
     */

    @Override public void enterLinetype(TestParser.LinetypeContext ctx) {

        query[count]=ctx.getText();
        //System.out.println("Line found!\n\n"+query[count]);
        count++;
    }


    @Override public void enterUnionattack(TestParser.UnionattackContext ctx) {

           bad[0][b] = Integer.toString(b+1);
           bad[1][b] = Integer.toString(count);
           bad[2][b] = "Union";
           bad[3][b] = ctx.getText().trim();
           b++;

        }

    @Override public void enterUnion(TestParser.UnionContext ctx) {
        //query[count]=ctx.getText();
        //count++;
    }

    @Override public void enterAltencoding(TestParser.AltencodingContext ctx) {
        bad[0][b]= Integer.toString(b+1);
        bad[1][b] = Integer.toString(count);
        bad[2][b] = "Alternative Encoding";
        bad[3][b] = ctx.getText().trim();
        b++;
    }

    @Override public void enterInference(TestParser.InferenceContext ctx) {
        bad[0][b]= Integer.toString(b+1);
        bad[1][b] = Integer.toString(count);
        bad[2][b] = "Inference";
        bad[3][b] = ctx.getText().trim();
        b++;
    }

        public String[][] getBadQuery() {return bad; }
        public int getBadQueryCount() { return b; }

        /*
        public String[] getTaut(){
            return taut;
        }
        public int getTautCount(){
            return t;
        }
        */

        public String[] getQuery(){
            return query;
        }
        
        public int getQueryCount(){
            return count;
        }
    } 
