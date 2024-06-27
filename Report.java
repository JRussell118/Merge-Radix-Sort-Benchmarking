/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project1;

/*Jaden Russell
  4/6/2024
  CMSC 451  
  This program creates takes the data from the file selected by the user to create
  create a table of the sort's benchmark data.
*/

import java.io.BufferedReader;
import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Report {

    private JFrame f;
    private JTable t;
    private String[][] data = new String[12][5];

    public Report() {
        JFileChooser file = new JFileChooser(new File("./src/project1/"));
        file.setFileFilter(new FileNameExtensionFilter(".txt files", "txt", "text"));
        file.showSaveDialog(null);

        data = getData(file.getSelectedFile());

        f = new JFrame("Benchmark Report");

        String[] columns = {"Size", "Avg Count", "Coef Count", "Avg Time", "Coef Time"};
        t = new JTable(data, columns);
        t.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(t);
        f.add(sp);
        // Frame Size
        f.setSize(500, 260);
        // Frame Visible = true
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private String[][] getData(File f) {

        String[][] d = new String[12][5];

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null || i < 12) {
                d[i] = line.trim().split(" ");
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }

        return d;
    }

}
