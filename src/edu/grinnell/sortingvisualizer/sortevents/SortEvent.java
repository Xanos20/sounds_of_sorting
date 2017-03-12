package edu.grinnell.sortingvisualizer.sortevents;

import java.util.ArrayList;
import java.util.List;

public interface SortEvent<T> {
  public <T extends Comparable<T>> void apply (ArrayList<T> list);
  public <T extends Comparable<T>> List<Integer> getAffectedIndices();
  public <T extends Comparable<T>> boolean isEmphasized();
}
