package edu.grinnell.sortingvisualizer.sorts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.grinnell.sortingvisualizer.sortevents.CompareEvent;
import edu.grinnell.sortingvisualizer.sortevents.SortEvent;
import edu.grinnell.sortingvisualizer.sortevents.SwapEvent;
import edu.grinnell.sortingvisualizer.sortevents.CopyEvent;

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
  
  
  /**
   * 
   * @param list
   * @return
   */
  public static <T> String listtoString(ArrayList<T> list) {
    // make sure there is an if element
    if (list.size() == 0) {
      return "[]";
    } else {
      StringBuffer buf = new StringBuffer();
      buf.append("[");
      buf.append(list.get(0));
      for (int i = 1; i < list.size(); i++) {
        buf.append(", ");
        buf.append(list.get(i));
      }
      buf.append("]");
      return buf.toString();
    }

  }
  
  
  /**
   * partition is a helper function for Quick Sort, dividing the array list into 2 sub-lists
   * @param list
   * @param low
   * @param hi
   * @param pivotIndex
   * @return
   * @throws IOException 
   */
  public static <T extends Comparable<T>> int partition(List<SortEvent<T>> event, ArrayList<T> list, int low, int hi, int pivotIndex) throws IOException {
    // null check
    if (list == null) {
      throw new IOException("Null ArrayList passed to partition");
    }
    // base case, when size <= 1
    if (hi - low <= 1) {
      return pivotIndex;
    }
    // second base case, if the list is size 2, there is no need to partition it into two separate array_lists 
    if (hi - low == 2) {
      System.out.println("List of size == 2 case reached");
      // swap the two elements if needed
      event.add(new CompareEvent<T>(low, low+1));
      if (list.get(low).compareTo(list.get(low+1)) > 0) {
        event.add(new SwapEvent<T>(low,low+1));
        swappy(list,low,low+1);
      }
      return pivotIndex;
    }
    
    // swap pivot value and last value to start the sorting of subarrays
    if (pivotIndex != hi-1) {
      event.add(new SwapEvent<T>(pivotIndex,hi-1));
      swappy(list, pivotIndex, hi-1);
      pivotIndex = hi-1;
      System.out.println("Swap the pivot and the last element");
    }
    int i = low;
    // set j to second to last position since the last element has already been swapped
    int j = hi - 2;
 
    System.out.println("The pivot element is" + list.get(pivotIndex));
    
    // sort 2 sub-arrays at the same time until the pointers meet in the middle
    while (i < j) {
      if (list.get(i).compareTo(list.get(pivotIndex)) >= 0) { 
        while (j > i) {
          System.out.println("Updated i= " + i + " j = " + j);
          event.add(new CompareEvent<T>(j, pivotIndex));
          if (list.get(j).compareTo(list.get(pivotIndex)) < 0) {
            event.add(new SwapEvent<T>(i,j));
            swappy(list, i,j);
            System.out.println(listtoString(list));
            break;  
          }
          j--;
        } 
      } else {
        i++;
      }
    }
    System.out.println("i = " + i);
    System.out.println("j = " + j);
    // put the pivot back into the middle position between the two sorted sub=arrays
    event.add(new SwapEvent<T>(pivotIndex,j));
    swappy(list, pivotIndex, j);
    return i;
  }
  
  
  /**
   * 
   * @param list
   * @param low
   * @param hi
   * @throws IOException
   */
  public static <T extends Comparable<T>> void quickSortHelper(List<SortEvent<T>> event, ArrayList<T> list, int low, int hi) throws IOException {
    if (hi - low > 1) {
      Random r = new Random();
      int pivotIndex = low + r.nextInt(hi-low);
      int midpoint = partition(event, list, low, hi, pivotIndex);
      System.out.println("midpoint is " + midpoint);
      // Sort the first subarray
      quickSortHelper(event, list, low, midpoint);

      // Sort the second subarray
      quickSortHelper(event, list, midpoint, hi);

      return;
    } else {
      System.out.println("Base Case Reached");
      return;
    }
  }
  
 
  /**
   * list contains 2 SORTED lists, marked by low to mid and mid to hi
   * @param list
   * @param low
   * @param mid
   * @param hi
   * mid and hi are exclusive bounds and low is an inclusive bound
   * @return void
   */
  private static <T extends Comparable<T>> void merge(List<SortEvent<T>> event, ArrayList<T> list, int low, int mid, int hi) {
    // Create new array to store results of merge
    ArrayList<T> results = new ArrayList<T>();
    
    // iterate first list from list[low] to list[mid]
    int i = low;
    int j = mid;
    // k is the position of results list
    while (i < mid && j < hi) {
      // if list[i] <= list[j] then add list[i] to results
      if (list.get(i).compareTo(list.get(j)) <= 0) {
        results.add(list.get(i));
        // increment i to next position in first list and results
        i++;
        
      }
      // if list[i] > list[j] then add list[i] to results
      if (list.get(i).compareTo(list.get(j)) > 0) {
        results.add(list.get(j));
        // increment j to next position in second list and results
        j++;
      } 
    }
    while (i < mid) {
      results.add(list.get(i));
      i++;
    }
    while (j < hi) {
      results.add(list.get(j));
      j++;
    }
    
    /* Copying the sorted buffer into the list */
    int k = 0;
    while (k < results.size()) {
      event.add(new CopyEvent<T>(low+k, results.get(k)));
      list.set(low+k, results.get(k));
      k++;
    }  
    return;
  } 


  /**
   * 
   * @param arr
   * @param low
   * @param hi
   */
  private static <T extends Comparable<T>> void mergeSortHelper(List<SortEvent<T>> event, ArrayList<T> list, int low, int hi) {
    int midpoint = low + (hi - low) / 2;
    if (hi > low + 1) {
      mergeSortHelper(event, list, low, midpoint);
      mergeSortHelper(event, list, midpoint, hi);
      merge(event, list, low, midpoint, hi);
    }
    return;

  }
  
  
  /**
   * For quicksort
   * @param list
   * @return
   * @throws IOException 
   */
  public static <T extends Comparable<T>> int medianIndex (ArrayList<T> list, int low, int hi) throws IOException {
    if (list == null) {
      throw new IOException("Null list passed to medianIndex");
    }
    T first = list.get(low);
    T middle = list.get((low+hi)/2);
    T last = list.get(hi - 1);
    if (first.compareTo(middle) >= 0) {
      if (middle.compareTo(last) >= 0) { return (low+hi)/2;} 
      else {
        if (first.compareTo(last) >= 0) { return hi-1;} else {return low;}
      }
    } else {
      if (middle.compareTo(last) >= 0) {
        if (first.compareTo(last) >= 0) { return low;} else {return hi-1;}
      } else { return (low+hi)/2;}
    }
    
  }



  /* -------- Sorting Algorithms ------------------------ */
  /**
   * selectionSort
   * @param arr
   * @return
   * @throws IOException 
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> selectionSort(ArrayList<T> list) throws IOException {
    if (list == null) {
      throw new IOException("Null ArrayList passed to selectionSort");
    }
    List<SortEvent<T>> event_list = new ArrayList<SortEvent<T>>();
    for (int i = 0; i < list.size(); i++) {
      int minIndex = i;
      for (int j = i+1; j < list.size(); j++) {
        event_list.add(new CompareEvent<T>(j, minIndex));
        if (list.get(j).compareTo(list.get(minIndex)) < 0 ) {
          minIndex = j;
        }
      }
      event_list.add(new SwapEvent<T>(i, minIndex));
      swappy (list, i, minIndex);
    }
    return event_list;
  }


  /**
   * insertionSort
   * @param arr
   * @return
   * @throws IOException 
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> insertionSort(ArrayList<T> list) throws IOException {
    // check for null case
    if (list == null) {
      throw new IOException("Null ArrayList passed to insertionSort");
    }
    List<SortEvent<T>> event_list = new ArrayList<SortEvent<T>>();
    for (int i = 1; i < list.size(); i++) {  
      for(int j = i; j > 0 && list.get(j-1).compareTo(list.get(j)) > 0; j--) {
        event_list.add(new CompareEvent<T>(j,j-1));
        event_list.add(new SwapEvent<T>(j,j-1));
        swappy(list, j, j-1);
        
      }
    }
    return event_list;
  }

  /**
   * mergeSort
   * @param arr
   * @return
   * @throws IOException
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> mergeSort(ArrayList<T> list) throws IOException {
    List<SortEvent<T>> event_list = new ArrayList<SortEvent<T>>();
    if (list == null) {
      throw new IOException("Null ArrayList passed to mergeSort");
    }
    
    if (list.size() <= 1) {
      System.out.println("ArraryList has one value or is empty");
    }

    if (list.size() > 1) {
      mergeSortHelper(event_list, list,0,list.size());
    }

    return null;

  }

  
  /**
   * quickSort
   * @param list
   * @return
   * @throws IOException
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> quickSort(ArrayList<T> list) throws IOException {
    List<SortEvent<T>> event_list = new ArrayList<SortEvent<T>>();
    if (list == null) {
      throw new IOException("ArrayList passed to quickSort is null");
    }
    if (list.size() <=1) {
      System.out.println("array has one value or is empty");
      return event_list;
    }
    if (list.size() > 1) {
      quickSortHelper(event_list, list, 0, list.size());
    }
    
    return event_list;
    
  }

  
  /**
   * bubbleSort
   * @param list
   * @return
   * @throws IOException
   */
  public static <T extends Comparable<T>> List<SortEvent<T>> bubbleSort(ArrayList<T> list) throws IOException {
    List<SortEvent<T>> event_list = new ArrayList<SortEvent<T>>();
    if (list == null) {
      throw new IOException("Null list passed to bubbleSort");
    }
    
    for (int i = 0; i < list.size(); i++) {
      for (int j = i+1; j < list.size();j++) {
        event_list.add(new CompareEvent<T> (i,j));
        if (list.get(j).compareTo(list.get(i)) < 0) {
          swappy(list, i, j);
          event_list.add(new SwapEvent<T>(i,j));
        }
      }
    }
    return null;
  }
  
  
  
  /**
   * 
   * @param list
   * @param events
   */
  public static <T extends Comparable<T>> void eventSort(ArrayList<T> list, List<SortEvent<T>> events) {
    for (int i = 0; i < events.size(); i++) {
      events.get(i).apply(list);
    }
  }
  

  
  
  
  
  
  
  
} // end class
