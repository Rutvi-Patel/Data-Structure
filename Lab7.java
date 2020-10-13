package ThreeTwoEight;
//Rutvi Patel
import java.util.*;

public class Lab7 {
    public static void main(String[] args) {
        mknodes a = new mknodes('a');
        mknodes b = new mknodes('b');
        mknodes c = new mknodes('c');
        mknodes d = new mknodes('d');
        mknodes e = new mknodes('e');
        mknodes f = new mknodes('f');
        mknodes g = new mknodes('g');
        mknodes h = new mknodes('h');
        mknodes[] arrNodes = {a, b, c, d, e, f, g, h};
        a.adj.add(d);a.adj.add(c);
        b.adj.add(c);b.adj.add(e);
        c.adj.add(a);c.adj.add(b);c.adj.add(d);
        d.adj.add(a);d.adj.add(e);d.adj.add(f);d.adj.add(c);
        e.adj.add(d);e.adj.add(b);e.adj.add(f);
        f.adj.add(d);f.adj.add(e);f.adj.add(h);
        h.adj.add(f);


        HashMap<mknodes, Integer> myDic = new HashMap<>();

        myDic = bfs(a);

        for (Map.Entry entry : myDic.entrySet()) {
            System.out.println("Vertex: " + entry.getKey() + "; Cost: " + entry.getValue());
        }
        Explore(arrNodes);
        for (mknodes each: arrNodes){
            System.out.println(each.value + ": " +each.color);

        }


    }

    public static HashMap bfs(mknodes a) {
        Queue<mknodes> fifo = new LinkedList<>();
        a.cost = 0;
        a.parent = a;
        fifo.add(a);
        mknodes temp;
        HashMap<mknodes, Integer> ans = new HashMap<>();
        while (!fifo.isEmpty()) {
            temp = fifo.poll();
            for (mknodes x : temp.adj) {
                if (x.parent == null) {
                    x.parent = temp;
                    x.cost = temp.cost + 1;
                    fifo.add(x);
                }
            }
            ans.put(temp, temp.cost);
        }
        return ans;
    }

    public static void Explore(mknodes[] arrVer) {
        for (mknodes each : arrVer) {
            if (each.color.equals("gray")) {
                each.color = "blue";
                Is_bipartite(each);
            }
        }
    }


    public static void Is_bipartite(mknodes a) {
        Queue<mknodes> fifo = new LinkedList<>();
        fifo.add(a);
        mknodes temp;
        while (!fifo.isEmpty()) {
            temp = fifo.poll();
            for (mknodes x : temp.adj) {
                if (x.color.equals("gray")) {
                    if (temp.color.equals("blue")) {
                        x.color = "red";
                    }else {
                        x.color = "blue";
                    }
                    fifo.add(x);
                }else if (temp.color.equals(x.color)) {
                    System.out.println("NOT bipartite");
                    break;
                }
//                break;
            }

        }
    }
}

class Nodes {
    char value;
    int cost;
    String color;
    mknodes parent;
    ArrayList<mknodes> adj;
    Nodes(char cha){
        value = cha;
        adj = new ArrayList<>();
        color = "gray";
    }
    public String toString(){
        return Character.toString(value);
    }
}
