package edu.grinnell.sortingvisualizer;

import java.awt.BorderLayout;


import javax.swing.JFrame;

public class SortingVisualizer {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    public static void main(String[] args) throws InterruptedException {        
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        //Color backgroundColor = new Color(116,169,207);
        //frame.getContentPane().setBackground(backgroundColor);
        NoteIndices notes = new NoteIndices(0);
        
        ArrayPanel   arrayPanel   = new ArrayPanel(notes, WIDTH, HEIGHT);
        ControlPanel controlPanel = new ControlPanel(notes, arrayPanel); 
        
        frame.setTitle("Sorting Visualizer");
        frame.add(controlPanel, BorderLayout.PAGE_END);
        frame.add(arrayPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
