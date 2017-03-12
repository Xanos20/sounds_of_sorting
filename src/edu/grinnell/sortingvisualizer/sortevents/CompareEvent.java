package edu.grinnell.sortingvisualizer.sortevents;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Sort Event = record indices of the array list while an event occurs (bridge between Sorts and Visualizer)


public class CompareEvent<T> implements SortEvent<T> {
  
  // Fields
  private int index1;
  private int index2;
  
  
  /**
   * Constructor
   * @param i
   * @param j
   */
  public CompareEvent(int i, int j) {
    this.index1 = i;
    this.index2 = j;
  }
  
  
  /**
   * 
   * @param list
   */
  public <T extends Comparable<T>> void apply (ArrayList<T> list){
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
   * Returns false since the list will not be changed
   * @return 
   */
  public <T extends Comparable<T>> boolean isEmphasized() {
    return false;
  }
  
}
