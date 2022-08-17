package SetPartition;

import java.util.Arrays;
import java.util.Random;


public class SetPartition {
    int numElements;
    int[] treeArray; //Array field to representing the tree

    SetPartition (int numElements){
        this.numElements = numElements; //initialising numElement
        treeArray = new int [numElements];
        for (int i = 0; i < treeArray.length; i++) { //for loop incrementing
            treeArray [i] = i; //filling the tree array
        }

    }

    //EXERCISE 2 - FINDING ROOTS
    public int getRoot(int x){
        int treeRoot = 0;
        int parentNode = treeArray[x];

        while (x != treeArray[x]) {
            x = parentNode;}
        treeRoot = treeArray[x];
        return treeRoot;
    }



    //EXERCISE 3 - IN THE SAME SUBSET
    public boolean inSameSubset (int x, int y){
        int j = getRoot(x);
        int k = getRoot(y);
        boolean b = j == k;

        return b;
    }

    //EXERCISE 4 - MERGING SUBSETS
    public void merge (int a, int b){
        int x = getRoot(a);
        int y = getRoot(b);
        if (x == y){
            System.out.println();
        }
        else {
            treeArray[y] = a;
        }
    }


    //EXERCISE 5 - DEPTH
    public int depth (int x) {
        int p = treeArray[x];

        int count = 0;

        while (x != treeArray[x]) {
            count++;
            x = p;
        }
        return count;
    }


    public int maxDepth (){
        int maxDepth = 0;

        for (int i = 0; i < treeArray.length; i++){
            int currentDep = depth(i);

            if (maxDepth < currentDep){


                maxDepth = currentDep;
            }
        }
        System.out.println("Max Depth: " + maxDepth);
        return maxDepth;
    }





    public static void main(String[] args) {
        SetPartition s = new SetPartition(1000);
        for (int i = 0; i < 1000; i++){ s.merge(i,(i + 1));}
        s.maxDepth();
        System.out.println(Arrays.toString(s.treeArray));


        SetPartition t = new SetPartition(1000);
        for (int i = 999; i > 0; i--) {
            int d = i - 1;t.merge(i, (i - 1));
        }
        t.maxDepth();
        System.out.println(Arrays.toString(t.treeArray));

        SetPartition u;
        for (int i = 0; i < 1000; i++) {
            SetPartition a = new SetPartition(1000);
        }
//Creates 1000 set partitions

        //Couldn't create 100 set partition
        /*SetPartition x = new SetPartition[1000];
        Random y = new Random();

        for (int i = 0; i < 1000; i++){
            x[i] = new SetPartition(1000);
        }*/


    }





}

