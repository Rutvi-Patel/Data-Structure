package ThreeTwoEight;

import java.util.ArrayList;
import java.util.LinkedList;

public class Lab8 {
    static int timer = 0;
    static LinkedList<mknodes> links = new LinkedList<>();

    public static void main(String[] args) {
        mknodes a = new mknodes('a');
        mknodes b = new mknodes('b');
        mknodes c = new mknodes('c');
        mknodes d = new mknodes('d');
        mknodes e = new mknodes('e');
        mknodes f = new mknodes('f');
        mknodes g = new mknodes('g');
        mknodes h = new mknodes('h');

        //Comment out graph 2 when testing graph 1.

//        graph 1
        mknodes[] arrNodes = {a, b, c, d, e, f,g};
        mknodes[][] edges1 = {{a, b}, {c, d}, {a, d}, {d, e}, {f, e}, {e, g}, {b, d}, {a, c}};
        Graph g1 = new Graph(edges1);
        Explore(arrNodes);


        //Comment out graph 1 when testing graph 2.

        // graph 2
//        Node[] arrNodes2 = {a, b, c, d, e, f};
//        Node[][] edges2 = {{a, b}, {a, c}, {b, c}, {b, d}, {b, e}, {c, e}, {e, b}, {e, d}, {d, f}, {f, e}};
//        Graph g2 = new Graph(edges2);
//        Explore(arrNodes2);



        //Printing the output
        System.out.println("Timer: " + timer);
        for(mknodes n: links) {
            System.out.println(n + "  start: " + n.startTime + " end: " + n.endTime);
        }


    }

    public static void Explore(mknodes[] vertices) {
        for (mknodes v : vertices) {
            if (v.parent == null) {
                v.parent = v;
                DFS(v);
            }
        }
    }

    public static void DFS(mknodes a) {
        a.startTime = ++timer;
        mknodes temp = a;
        for (mknodes n : a.adj) {
            if (n.startTime != 0 && n.endTime == 0) {
                System.out.println("Edge: " + temp + " to " + n + " causes cycle \nCycle detected, topological sort is impossible");
                break;
            }
            else if (n.parent != null){
                continue;
            }
            else {
                n.parent = temp;
                DFS(n);
            }
        }
        a.endTime = ++timer;
        links.add(a);
    }
}


    class mknodes {
        char value;
        int cost;
        String color;
        mknodes parent;
        int startTime;
        int endTime;
        ArrayList<mknodes> adj;

        mknodes(char cha) {
            value = cha;
            adj = new ArrayList<>();
            color = "gray";
            startTime = 0;
            endTime = 0;
        }

        public String toString() {
            return Character.toString(value);
        }
    }

    class Graph {
        mknodes[][] edges;

        Graph(mknodes[][] e) {
            edges = e;
            for (int i = 0; i < edges.length; i++) {
                edges[i][0].adj.add(edges[i][1]);
            }
        }
    }
