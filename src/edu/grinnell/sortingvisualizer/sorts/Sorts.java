package edu.grinnell.sortingvisualizer.sorts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.grinnell.sortingvisualizer.sortevents.SortEvent;

public class Sorts {

  /* ----------- Helper Functions ---------------------- */
  /**
   * swappy swaps 2 positions in an array
   * @param arr an array of T objects
   * @param i an integer which is the first given index
   * @param j an integer which is the second given index
   */
  public static <T> void swappy (ArrayList<T> list, int i, int j) {
    T temp = list.get(i);
    list.set(i,  list.get(j) );
    list.set(j,  temp);
  } 
  
  
  public static <T extends Comparable<T>> int partition(ArrayList<T> list, int low, int hi, int pivotIndex) {
    if (list.size() <= 1) {
      return pivotIndex;
    }
    if (pivotIndex != hi-1) {
      swappy(list, pivotIndex, hi-1);
      pivotIndex = hi-1;
    }
    int i = low;
    int j = hi - 2;
    int midpoint = (hi-low)/2;
    while (i <= midpoint) {
      if (list.get(i).compareTo(list.get(pivotIndex)) > 0) {
        while (j >= midpoint) {
          if (list.get(j).compareTo(list.get(pivotIndex)) < 0) {
            swappy(list, i,j);
          }
          j++;
        }
        i++;
      }
    }
    swappy(list, pivotIndex, midpoint);
    return midpoint;
  }


  /* -------- Sorting Algorithms ------------------------ */
  /**
   * 
   * @param arr
   * @return
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(ArrayList<T> list) {
    for (int i = 0; i < list.size(); i++) {
      int minIndex = i;
      for (int j = i+1; j < list.size(); j++) {
        if (list.get(j).compareTo(list.get(minIndex)) < 0 ) {
          minIndex = j;
        }
      }
      swappy (list, i, minIndex);
    }
    return null;
  }


  /**
   * 
   * @param arr
   * @return
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> insertionSort(ArrayList<T> list) {
    for (int i = 0; i < list.size(); i++) {
      for(int j = i+1; 
          j > 0 && list.get(j-1).compareTo(list.get(j)) < 0;
          j--) {
        swappy(list, j-1, j);
      }
    }
    return null;
  }


  /**
   * list contains 2 SORTED lists, marked by low to mid and mid to hi
   * @param list
   * @param low
   * @param mid
   * @param hi
   * mid and hi are exclusive bounds and
   *  low is an inclusive bound
   * @return void
   */
  private static <T extends Comparable<T>> void merge(ArrayList<T> list, int low, int mid, int hi) {
    // Create new array to store results of merge
    ArrayList<T> results = new ArrayList<T>();
    // iterate first list from list[low] to list[mid]
    int i = low;
    int j = mid;
    // k is the counter of results array
    // k is the position of results list
    while (i < mid && j < hi) {
      // if list[i] <= list[j] then add list[i] to results
      if (list.get(i).compareTo(list.get(j)) < 0) {
        results.add(list.get(i));
        // increment i & k to next position in first list and results
        i++;
      }
      // if list[i] > list[j] then add list[i] to results
      if (list.get(i).compareTo(list.get(j)) > 0) {
        results.add(list.get(j));
        // increment j & k to next position in second list and results
        j++;
      } 
    }
    return;

  } 


  /**
   * 
   * @param arr
   * @param low
   * @param hi
   */
  private static <T extends Comparable<T>> void mergeSortHelper(ArrayList<T> list, int low, int hi) {
    if (hi > low) {
      int midpoint = low + (hi - low) / 2;
      mergeSortHelper(list, low, midpoint);
      mergeSortHelper(list, midpoint + 1, hi);
      merge(list, low, midpoint, hi);
    }
    return;

  }


  /**
   * 
   * @param arr
   * @return
   * @throws IOException
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(ArrayList<T> list) throws IOException {

    if (list == null) {
      throw new IOException("Array is null");
    }

    if (list.size() <= 1) {
      System.out.println("arrary has one value or is empty");
    }

    if (list.size() > 1) {
      mergeSortHelper(list,0,list.size());
    }

    return null;

  }


  public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(T[] arr) {
    // TODO: implement instrumented quickSort
    return null;
  }

  public static <T extends Comparable<T>> List<SortEvent<T>> customSort(T[] arr) {
    // TODO: implement your own custom sort
    return null;
  }

  /* Main */
  public static <T extends Comparable<T>> void main (String[] args) throws IOException {
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(5, 1, 8, 4, 3, 6, 7, 2, 1, 13));
    mergeSort(list);
    for (int i = 0; i < list.size();i++) {
      System.out.println(list.get(i));
    }
  }

  
  
  
  
  
  
  
} // end class
