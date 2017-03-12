package edu.grinnell.sortingvisualizer.sorts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SortTest {

  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    //ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(13,2,34,0,3,7,9,10,1));
    
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(3,9,2,8,6,4,1,7,5,5));
    //ArrayList<Integer> list2 = new ArrayList<Integer>(Arrays.asList(3,2));
    //int pivot = Sorts.medianIndex(list, 0, list.size());
    Sorts.insertionSort(list);
    
    for (int i = 0; i < list.size();i++) {
      System.out.println(list.get(i));
    }
    
  }

}
