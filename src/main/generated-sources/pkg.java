public class pkg{
    private String[] test=null;
    private int count=0;
    private list list=null;
    
    public pkg(){
    
    }
    
    public pkg(String[] t, int c, list l){
        test=t;
        count=c;
        list=l;
    }
    
    public pkg(String[] t, int c){
        test=t;
        count=c;
    }
    
    public void setArray(String[] t){
        test=t;
    }
    
    public void setCount(int c){
        count=c;
    }
    
    public void setList(list l){
        list=l;
    }
    
    public String[] getArray(){
        return test;
    }
    
    public int getCount(){
        return count;
    }
    
    public list getList(){
        return list;
    }
}
