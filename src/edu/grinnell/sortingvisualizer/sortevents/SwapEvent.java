package edu.grinnell.sortingvisualizer.sortevents;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.grinnell.sortingvisualizer.sorts.Sorts;

public class SwapEvent<T> implements SortEvent<T> {
  
  // fields
  private int index1;
  private int index2;
  
  
  /**
   * 
   * @param i
   * @param j
   */
  public SwapEvent(int i, int j) {
    this.index1 = i;
    this.index2 = j;
  }
  
  
  /**
   * 
   * @param list
   */
  public <T extends Comparable<T>> void apply (ArrayList<T> list){
    Sorts.swappy(list, this.index1, this.index2);
    return;
  }
  
  
  /**
   * 
   * @return
   */
  public <T extends Comparable<T>> List<Integer> getAffectedIndices(){
    return Arrays.asList(this.index1, this.index2);
  }
  
  
  /**
   * 
   * @return
   */
  public <T extends Comparable<T>> boolean isEmphasized() {
    return true;
  }
  
}
