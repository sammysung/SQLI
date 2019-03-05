package com.sqli.framework;

import java.util.*;

public class taint{
    String r=null;
    public taint(pkg pkg){
        r="This is a list of all the queries that did not match marked models.\n\n";
        String qu="";
        boolean safe=false;
        list bad=new list();
        int index=pkg.getCount();
        list list=pkg.getList();
        String[] q=pkg.getArray();
        for(int i=0; i<index; i++){
            node current=list.first;
            while(current!=null){
                qu=current.getQuery();
                if(qu.equals(q[i]))
                    safe=true;
                current=current.next;
            }
            if(!safe){
                bad.add(safe, q[i], 0);
                r+=q[i]+"\n";
            }
            safe=false;
        }
        System.out.println("These are the bad ones.\n\n");
        bad.show();
    }
    public String re(){
        return r;
    }
}
