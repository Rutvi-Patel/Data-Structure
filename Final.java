package ThreeTwoEight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Final {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

//Problem 1*******************************************
        System.out.println("Problem 1: ");
        //Creating Nodes
        gnodes a = new gnodes('a');
        gnodes b = new gnodes('b');
        gnodes c = new gnodes('c');
        gnodes d = new gnodes('d');
        gnodes e = new gnodes('e');
        gnodes f = new gnodes('f');
        gnodes g = new gnodes('g');
        gnodes h = new gnodes('h');
        //Vertices array
        ArrayList<gnodes> arrNodes = new ArrayList<>();
        arrNodes.add(g); arrNodes.add(b); arrNodes.add(c); arrNodes.add(d); arrNodes.add(e);
        arrNodes.add(f); arrNodes.add(a); arrNodes.add(h);

//    Creating the graph
        Object[][] edges1 = {{a, b, 15},{b, a, 15}, {a, d,3},{d, a,3}, {a, c, 2}, {c, a, 2},
                {d, e, 1}, {e, d, 1}, {b, f, 1}, {f, b, 1}, {b, c, 8}, {c, b, 8}, {c, f, 7}, {f, c, 7},
                {c,g, 5}, {g,c, 5}, {f,g,2}, {g,f,2},{g,h,1}, {h,g,1},{e,b,2},{b,e,2}};
        Graphing g1 = new Graphing(edges1);

       //Algorithm
        //preparing for the algorithm
        infinity(arrNodes);//--------------------------------O(v)
        System.out.println("The shortest path: ");
        Prims(a,arrNodes);//------------------------------O(ElogV)
        System.out.println("\n");


//Problem 2***************************************************************
        System.out.println("Problem 2:");
        System.out.println("Enter number of elements");
        int x = scnr.nextInt();
        //getting inputs
        ArrayList<cNodes> Narr = new ArrayList();
        String str;
        for (int i=0;i<x;i++ ){
            str = scnr.next();
            Narr.add(new cNodes(str));
   }
        //Adding to adj list for each node aka creating graph
        addToAdj(Narr); //--------------------------------- O(v^2)
        //Problem 2 Algorithm
        System.out.println(DFS(Narr.get(0), Narr));//----------------------O(v^2)

    }




//----------------------------------------------Problem 1 Methods --------------------------------------------------------

    public static void infinity(ArrayList<gnodes> vertices) {//-----------------------O(v)
        for (gnodes v : vertices) {
            v.cost = Integer.MAX_VALUE;
        }
    }
    public static void Prims( gnodes a , ArrayList<gnodes> v){//---------------------------O(V^2 + VlogV)
        a.parent = a;
        a.cost = 0;
        build_MinHeap(v);
        System.out.println(v);

        while (!v.isEmpty()){//--------------------------------O(V)
            System.out.println(a+" --> "+v.get(0)+": "+ v.get(0).cost);
            gnodes temp = delete_MinHeap(v);//--------------------------O(logV)
            for (gnodes each: temp.adjMap.keySet()){
                if (each.cost > weight(temp.adjMap.get(each),temp.cost)){
                    each.cost = weight(temp.adjMap.get(each),temp.cost);
                    each.parent = temp;
                }
            }
            build_MinHeap(v);//------------------------O(V)
        }
    }


    public static int weight (int a, int b){
        return a + b;
    }//--------------------O(1)



    // All min Heap methods
    public static void build_MinHeap ( ArrayList<gnodes> v ){ //----------------------O(v)
        int n = v.size();
        for (int i = n/2; i > -1; i--){
            min_heapify(v, i,n);
        }
    }


    public static gnodes delete_MinHeap (ArrayList<gnodes> v){ //-------------------O(logV)
        int n = v.size()-1;
        gnodes temp = v.get(0);
      v.set(0,v.get(n));
      v.remove(n);
      min_heapify(v,0, n);// -------O(logV)
      return temp;
    }

    public static void min_heapify(ArrayList<gnodes> arr, int i, int ei) {//-----------O(logV)
        int li = 2 * i + 1;
        int ri = 2 * i + 2;
        int ans = i;
        if (li<ei && arr.get(ans).cost > arr.get(li).cost){
            ans = li;
        }
        if (ri < ei && arr.get(ans).cost>arr.get(ri).cost){
            ans = ri;
        }
        if (i != ans){
            gnodes temp = arr.get(i);
            arr.set(i,arr.get(ans));
            arr.set(ans, temp);
            min_heapify(arr, ans, ei);
        }
    }


//    ---------------------------- Problem 2 methods ----------------------------------------------------------------


    public static void addToAdj (ArrayList<cNodes> v){//------------------------------------O(V^2)
        for (int i =0 ; i< v.size() ; i++){
            for (int j = i+1 ; j < v.size(); j++){
                if (v.get(i).startChar == v.get(j).endChar || v.get(i).endChar == v.get(j).startChar ){
                    v.get(i).adj.add(v.get(j));
                    v.get(j).adj.add(v.get(i));
                }
            }
        }
    }

static int timer = 0;
    public static boolean DFS(cNodes a, ArrayList<cNodes> arr) {//-------------------------------O(E)
        a.startTime = ++timer;
        a.parent = a;
        cNodes temp = a;
        for (cNodes n : a.adj) {
            if (n.endChar == a.startChar && n.startTime!=0){
                continue;
            }
            if (n.startTime != 0 && n.endTime == 0) {
                if (arr.size() == 1){
                    return  true;
                }
            }
            else if (n.parent != null){
                continue;
            }
            else {
                n.parent = temp;
                arr.remove(a);
                if(DFS(n,arr )){
                    return true;
                }
            }
        }
        a.endTime = ++timer;
        return false;
    }

}
//---------------------------------------------Relevant classes --------------------------------------------------
//Nodes class for problem 1
class gnodes {
    char letter;
    int cost;
    String color;
    gnodes parent;
    int startTime;
    int endTime;
    boolean visited;
    HashMap<gnodes,Integer> adjMap;
    gnodes(char cha) {
        letter = cha;
        adjMap = new HashMap<>();
        color = "gray";
        startTime = 0;
        endTime = 0;
        visited = false;
    }
    public String toString() {
        return Character.toString(letter);
    }
}

//Nodes class for problem 2
class cNodes{
    String word;
    char startChar;
    char endChar;
    cNodes parent;
    int startTime;
    int endTime;
    ArrayList<cNodes> adj;
    cNodes(String s){
        word = s;
        startChar = word.charAt(0);
        endChar = word.charAt(word.length()-1);
        adj = new ArrayList<>();
        startTime = 0;
        endTime = 0;
    }
    public String toString() {
        return word;
    }
}



//Graph class
class Graphing {
    Object[][] edges;
    Graphing(Object[][] e) {
        edges = e;
        for (int i = 0; i < edges.length; i++) {
            gnodes g = (gnodes)edges[i][0];
            gnodes f = (gnodes)edges[i][1];
            int cost = (int)edges[i][2];
            g.adjMap.put(f,cost);
        }
    }
}

