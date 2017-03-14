package edu.grinnell.sortingvisualizer.sorts;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class MergeSortTest {

  
  @Test
  public void test1() throws IOException {
    // Case 1: Even number of elements, containing duplicates
    ArrayList<Integer> arr1 = new ArrayList<Integer>(Arrays.asList(3, 4, 4, 1, 5, 3, 5, 1));
    ArrayList<Integer> arr1_correct = new ArrayList<Integer>(Arrays.asList(1, 1, 3, 3, 4, 4, 5, 5));
    Sorts.mergeSort(arr1);
    assertEquals(arr1_correct, arr1);
  }

  @Test
  public void test2() throws IOException {
 // Case 2: Odd number of elements
    ArrayList<Integer> arr2 = new ArrayList<Integer>(Arrays.asList(3, 1, 4, 5, 7, 9, -1));
    ArrayList<Integer> arr2_correct = new ArrayList<Integer>(Arrays.asList(-1, 1, 3, 4, 5, 7, 9));
    Sorts.mergeSort(arr2);
    assertEquals(arr2_correct, arr2);
  }
  
  @Test
  public void test3() throws IOException {
 // Case 3: All same elements
    ArrayList<Integer> arr3 = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
    ArrayList<Integer> arr3_correct = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
    Sorts.mergeSort(arr3);
    assertEquals(arr3_correct, arr3);
  }
  
  @Test
  public void test4() throws IOException {
 // Case 4: An already sorted list
    ArrayList<Integer> arr4 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    ArrayList<Integer> arr4_correct = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
    Sorts.mergeSort(arr4);
    assertEquals(arr4_correct, arr4);
    
  }
  
  @Test
  public void test5() throws IOException {
    // Case 5: An empty list
    ArrayList<Integer> arr5 = new ArrayList<Integer>();
    ArrayList<Integer> arr5_correct = arr5;
    Sorts.mergeSort(arr5);
    assertEquals(arr5_correct, arr5);
    
  }
  
  
  @Test
  public void test6() throws IOException {
 // Case 6: A list of size 1
    ArrayList<Integer> arr6 = new ArrayList<Integer>(Arrays.asList(2));
    ArrayList<Integer> arr6_correct = new ArrayList<Integer>(Arrays.asList(2));
    Sorts.mergeSort(arr6);
    assertEquals(arr6_correct, arr6);
  }
  
  @Test
  public void test7() throws IOException {
 // Case 7: A list of size 2
    ArrayList<Integer> arr6 = new ArrayList<Integer>(Arrays.asList(2,1));
    ArrayList<Integer> arr6_correct = new ArrayList<Integer>(Arrays.asList(1,2));
    Sorts.mergeSort(arr6);
    assertEquals(arr6_correct, arr6);
  }
  
  @Test
  public void test8() throws IOException {
 // Case 8: A reversely sorted list
    ArrayList<Integer> arr6 = new ArrayList<Integer>(Arrays.asList(9, 8, 7, 6, 5, 4, 4, 3, 2, 1, 0));
    ArrayList<Integer> arr6_correct = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,4,5,6,7,8,9));
    Sorts.mergeSort(arr6);
    assertEquals(arr6_correct, arr6);
  }
 

}
