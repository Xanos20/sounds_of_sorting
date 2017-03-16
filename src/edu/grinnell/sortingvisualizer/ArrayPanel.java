package edu.grinnell.sortingvisualizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

    private NoteIndices notes;
    private int maxWidth;
    private int maxHeight;
    
    /**
     * Constructs a new ArrayPanel that renders the given note indices to
     * the screen.
     * @param notes the indices to render
     * @param width the width of the panel
     * @param height the height of the panel
     */
    public ArrayPanel(NoteIndices notes, int width, int height) {
        this.notes = notes;
        this.setPreferredSize(new Dimension(width, height));
        this.maxWidth = width;
        this.maxHeight = height;
    }

    @Override
    public void paintComponent(Graphics g) {
      // Draw n bars, each bar represents an index in the notes
      int numBars = this.notes.getNotes().size();
      int barWidth = (int) ((double)this.maxWidth / (double)numBars);
      int barHeight;
      for (int i = 0; i < numBars; i++) {
        // barHeight is proportional to the index/ the max index
        // We added 1 because the lowest index is 0
        barHeight = (int)((double) this.maxHeight * (double) (this.notes.getNotes().get(i)+1) / (double) numBars);
        // Fill the bar with magenta (if not highlighted) or red (if highlighted)
        if (this.notes.highlights[i]) {
          g.setColor(Color.RED);
          this.notes.highlights[i] = false;
        } else {
          int barIndex = this.notes.getNotes().get(i);
          Color customColor = new Color(230 - barIndex*(230/numBars), 240 - barIndex*(240/numBars), 245 - barIndex*(245/numBars)); 
          g.setColor(customColor);
        }
        g.fillRect(barWidth*i, this.maxHeight - barHeight, barWidth, barHeight);
      }
    }
}