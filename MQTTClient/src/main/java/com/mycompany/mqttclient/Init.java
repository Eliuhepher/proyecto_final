/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqttclient;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Init
{
        public static DefaultCategoryDataset createDataset( ) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue( 15 , "schools" , "1970" );
            dataset.addValue( 30 , "schools" , "1980" );
            dataset.addValue( 60 , "schools" ,  "1990" );
            dataset.addValue( 120 , "schools" , "2000" );
            dataset.addValue( 240 , "schools" , "2010" );
            dataset.addValue( 300 , "schools" , "2014" );
            return dataset;
        }

	public static void main(String[] args) 
	{		
		
               
                JFreeChart chart2 = ChartFactory.createLineChart("segunda grafica", "hola" , "hola", createDataset());

                ChartPanel panel2 = new ChartPanel(chart2);
		
		//Creamos la ventana
		JFrame ventana = new JFrame("Grafica");
		ventana.setVisible(true);
		ventana.setSize(800, 600);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventana.add(panel2);
		

	}

}

