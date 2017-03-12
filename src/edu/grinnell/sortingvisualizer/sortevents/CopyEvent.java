package edu.grinnell.sortingvisualizer.sortevents;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyEvent<T> implements SortEvent<T> {
  private int index;
  private T value;
  
  public CopyEvent (int i, T value) {
    this.index = i;
    this.value = value;
  }
  public <T extends Comparable<T>> void apply (ArrayList<T> list){
    list.set(this.index, (T) this.value); //????? Why do we need to cast value to T?
  }
  
  public <T extends Comparable<T>> List<Integer> getAffectedIndices(){
    return Arrays.asList(this.index);
  }
  
  public <T extends Comparable<T>> boolean isEmphasized() {
    return true;
  }
}
