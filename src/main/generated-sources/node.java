
/*
    Pretty standard node implementation, with return methods for safety status, id, and the query associated.
 */

public class node{
    private boolean safe;
    private String query;
    private int id;
    node next;
    
    public node(boolean s, String q, int i){
        safe=s;
        query=q;
        id=i;
    }
    
    public void show(){
        System.out.println("The query is: "+query);
        System.out.println("The query ID is: "+id);
        System.out.println("The query safety status is: "+safe);
    }
    
    public boolean getSafety(){
        return safe;
    }
    
    public String getQuery(){
        return query;
    }
    
    public int getID(){
        return id;
    }
}
