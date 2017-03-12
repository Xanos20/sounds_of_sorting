package edu.grinnell.sortingvisualizer.sortevents;
import java.util.ArrayList;
import java.util.List;

public class SwapEvent<T> implements SortEvent<T> {
  public <T extends Comparable<T>> void apply (ArrayList<T> list){
    return;
  }
  
  public <T extends Comparable<T>> List<Integer> getAffectedIndices(){
    return null;
  }
  
  public <T extends Comparable<T>> boolean isEmphasized() {
    return true;
  }
}
