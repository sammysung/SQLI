public class driver{
    // f
    private boolean termFile=false;
    // o
    private boolean outputFile=false;
    // v
    private boolean verbose=false;
    //i
    private boolean interactive=false;
    // n
    private boolean aTest=false;
    // t
    private boolean tTest=false;
    // w
    private boolean overWrite=false;
    // u
    private boolean selected=false;
    // d
    private boolean database=false;
    // b
    private boolean DBcheck=false;
    
    public driver(){
    
    }
    
    public void setTerm(boolean f){
        termFile=f;
    }
    
    public void setOutput(boolean o){
        outputFile=o;
    }
    
    public void setVerbose(boolean v){
        verbose=v;
    }
    
    public void setInteractive(boolean i){
        interactive=i;
    }
    
    public void setATest(boolean a){
        aTest=a;
    }
    
    public void setTTest(boolean t){
        tTest=t;
    }
    
    public void setOverWrite(boolean w){
        overWrite=w;
    }
    
    public void setSelection(boolean s){
        selected=s;
    }
    
    public void setSafe(boolean s){
        database=s;
    }
    
    public void setCheck(boolean c){
        DBcheck=c;
    }
    
    public boolean getTerm(){
        return termFile;
    }
    
    public boolean getOutput(){
        return outputFile;
    }
    
    public boolean getVerbose(){
        return verbose;
    }
    
    public boolean getInteractive(){
        return interactive;
    }
    
    public boolean getATest(){
        return aTest;
    }
    
    public boolean getTTest(){
        return tTest;
    }
    
    public boolean getOverWrite(){
        return overWrite;
    }
    
    public boolean getSelection(){
        return selected;
    }
    
    public boolean getSafe(){
        return database;
    }
    
    public boolean getCheck(){
        return DBcheck;
    }
}
