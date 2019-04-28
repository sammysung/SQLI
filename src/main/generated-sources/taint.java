import java.util.*;

public class taint {
    String r = " ";

    public taint(String[] q, int index, list list) {
        //r=null;//"This is a list of all the queries that did not match marked models.\n\n";
        //System.out.println("Starting the tainting test...\n\n\n\n");

        String qu = "";
        String pu = "";
        boolean safe = false;
        list bad = new list();
        int check=0;
        //System.out.println("Showing the original list");
        //list.show();
        //System.out.println("\n\n\n");
        //int index=pkg.getCount();
        //list list=pkg.getList();
        //String[] q=pkg.getArray();
        if (index <= 0) {
            System.out.println("Tainting test is not running!");
            System.exit(1);
            return;
        }
        for (int i = 0; i < index; i++) {
            node current = list.first;
            while (current != null) {
                qu = current.getQuery();
                qu=qu.trim();
                qu=qu.replaceAll("[\\t\\n\\r]+"," ");
                //System.out.println("\n"+qu+"\n");
                pu=q[i];
                pu=pu.trim();
                pu=pu.replaceAll("[\\t\\n\\r]+"," ");
                //System.out.println(pu+"\n");
                if (qu.equalsIgnoreCase(pu)) {
                    safe = true;
                    //System.out.println("You're safe now...\n\n"+pu);
                    break;
                }
                current = current.next;
            }
            if (!safe) {
                bad.add(false, q[i], 0);
                r += q[i] + "\n";
                //System.out.println("Gotcha!\n\n"+check+"\n\n"+q[i].trim());
                //check++;
            }
            safe = false;
            //System.out.println(i);
        }
        //System.out.println("These are the bad ones.\n\n");
        //bad.show();
        //System.out.println("\n\n\n\n");
    }

    public String re() {
        return r;
    }
}