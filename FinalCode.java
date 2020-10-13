import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class FinalCode {

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
        arrNodes.add(g);
        arrNodes.add(b);
        arrNodes.add(c);
        arrNodes.add(d);
        arrNodes.add(e);
        arrNodes.add(f);
        arrNodes.add(a);
        arrNodes.add(h);

//    Creating the graph
        Object[][] edges1 = {{a, b, 15}, {b, a, 15}, {a, d, 3}, {d, a, 3}, {a, c, 2}, {c, a, 2},
                {d, e, 1}, {e, d, 1}, {b, f, 1}, {f, b, 1}, {b, c, 8}, {c, b, 8}, {c, f, 7}, {f, c, 7},
                {c, g, 5}, {g, c, 5}, {f, g, 2}, {g, f, 2}, {g, h, 1}, {h, g, 1}, {e, b, 2}, {b, e, 2}};
        Graphing g1 = new Graphing(edges1);

        //Algorithm
        //preparing for the algorithm
        infinity(arrNodes);//--------------------------------O(v)
        System.out.println("The shortest path: ");
        Prims(a, arrNodes);//------------------------------O(ElogV)
        System.out.println("\n");

    //Problem 2******************************************

        ArrayList<String> input  =  new ArrayList<>();
        System.out.println("Problem 2:");
        System.out.println("Enter number of elements");
        int x = scnr.nextInt();
        //getting inputs
        String str;
        for (int i=0;i<x;i++ ){
            str = scnr.next();
            input.add(str);
        }
        String beginWord = input.get(0);
        int index  = 0;
         ArrayList<Integer> edges = new ArrayList<>();
        for (int i= 0; i<x;i++){
            edges.add(0);
        }
        System.out.println(input);
        System.out.println(edges);
        CreateGraphMethod(input, beginWord,index,edges);


    }




//----------------------------------------------Problem 1 Methods --------------------------------------------------------

    public static void infinity(ArrayList<gnodes> vertices) {
        for (gnodes v : vertices) {
            v.cost = Integer.MAX_VALUE;
        }
    }
    public static void Prims( gnodes a , ArrayList<gnodes> v){
        a.parent = a;
        a.cost = 0;
        build_MinHeap(v);
        System.out.println(v);

        while (!v.isEmpty()){//--------------------------------O(V)
            System.out.println(a+" --> "+v.get(0)+": "+ v.get(0).cost);
            gnodes temp = delete_MinHeap(v);
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
    }



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

    //-------------------------------------Problem 2 Methods--------------------------------------------------------

    public static void CreateGraphMethod(ArrayList<String> arr, String a, int index, ArrayList<Integer> edges ){
        for (int i = index; i<arr.size();i++){
            String u = arr.get(i);
            if (a.equals(u)){
                //do nothing
            }
            else {
                if (edges.contains(0)) {
                    if (a.charAt(u.length() - 1) == u.charAt(0)) {
                        if (edges.get(arr.indexOf(a)) == 0) {
                            edges.set(arr.indexOf(a), arr.indexOf(u));
                            a = arr.get(arr.indexOf(u));
                            System.out.println(edges);
                            CreateGraphMethod(arr, a, index + 1, edges);
                        }
                    } else {
                        if (i < arr.size() - 1) {
                            u = arr.get(index + 1);
                            index += 1;
                        }
                    }
                }
            }
        }
    }


//    public static void cyclic(ArrayList<String> arr){
//        for (int i = 0;i<arr.size();i++){
//            if (arr.get(i))
//        }
//    }



}


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

