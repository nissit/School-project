  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datavisualisation;

/**
 *
 * @author Miss T
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Functionalities {
    public List<String[]> readCSV(String filePath) {
    List<String[]> data = new ArrayList<>();
    String line;
    String csvSplitBy = ",";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        while ((line = br.readLine()) != null) {
            // Use comma as separator
            String[] row = line.split(csvSplitBy);
            data.add(row);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    System.out.println(data);
    return data;
}
 
    public static void main(String[]args){
        
        Functionalities myFunctionalities = new Functionalities();
        myFunctionalities.readCSV("Book1.csv");
        //String myData = myFunctionalities.readCSV("Desktop/Copy of Easter_Concert_Wear(1).csv");
    }
}
