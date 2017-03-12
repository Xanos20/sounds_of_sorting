package edu.grinnell.sortingvisualizer;

import java.util.ArrayList;
import java.util.Random;

/**
 * A collection of indices into a Scale object.
 * These indices are the subject of the various sorting algorithms
 * in the program.
 */
public class NoteIndices {

  // Fields
  public Integer[] IndicesArr;
  public boolean[] highlights;


  /**
   * Constructor
   * @param n the size of the scale object that these indices map into
   */
  public NoteIndices(int n) {
    this.IndicesArr = new Integer[n];
    this.highlights = new boolean[n];
  }


  /**
   * Reinitializes this collection of indices to map into a new scale object
   * of the given size.  The collection is also shuffled to provide an
   * initial starting point for the sorting process.
   * @param n the size of the scale object that these indices map into
   */
  public void initializeAndShuffle(int n) {
    this.IndicesArr = new Integer[n];
    this.highlights = new boolean[n];
    Random r = new Random();
    ArrayList<Integer> out = new ArrayList<Integer>();
    
    // find random numbers from [0, n - 1] and put each of them into the array and exclude repeated values 
    while (out.size() < n) {
      int randNum = r.nextInt(n);
      if (out.contains(randNum) == false) {
        out.add(randNum);
      }      
    } 
    
    // put all elements of out into IndicesArr
    for (int i = 0; i < n; i++) {
      this.IndicesArr[i] = out.get(i);
    }
    
    return;
    
  }


  /** @return the indices of this NoteIndices object */
  public Integer[] getNotes() { 
    return this.IndicesArr; 
  }


  /**
   * Highlights the given index of the note array
   * @param index the index to highlight
   */
  public void highlightNote(int index) {
    highlights[index] = true;
  }


  /** @return true if the given index is highlighted */
  public boolean isHighlighted(int index) {
    // TODO: fill me in
    return highlights[index];
  }


  /** Clears all highlighted indices from this collection */
  public void clearAllHighlighted() {
    for (int i = 0; i < this.highlights.length; i++) {
      highlights[i] = false;
    }
  }


}
