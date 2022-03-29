package clase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.Font.BOLD;
import static java.awt.Font.SERIF;


public class GUI {

    JFrame frame;  //frame cu tabel
    JFrame frame1; //frame cu restul informatiilor
    JPanel panel = new JPanel();
    JTable table; //table


    public GUI(String[][] data) {

        frame = new JFrame();
        // Frame Title
        frame.setTitle("Analiza Limbii Romane");

        // Datele tabelului
        //le dau in main

        // Numele coloanelor
        String[] columnNames = {"Litera", "Numar aparitie", "Frecventa", "Cod Huffman"};

        // Initializing the JTable

        table = new JTable(data, columnNames);

        table.setBounds(30, 40, 200, 300);
        table.setFont(Font.getFont(SERIF));


        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        // Frame Size
        frame.setSize(2000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Frame Visible = true
        frame.setVisible(true);
    }

    public GUI(double entropie, double lungimeMedie, double eficenta, double redundanta, int total) {
        // aici afisez entropia
        // verificarea Teoremei Shannon
        // H(A)
        // M =2(se foloseste cod binar)
        // lungimea medie a combinatiilor de cod
        // eficienta
        // redundanta
        frame1 = new JFrame();
        // Frame Title
        frame1.setTitle("Analiza Limbii Romane");

        // aici afisez entropia

        JLabel label1 = new JLabel("H(A)= " + entropie, SwingConstants.CENTER);
        label1.setFont(new Font("Serif", BOLD,40));
        label1.setBorder(new EmptyBorder(0, 100, 0, 100));
        JLabel label2 = new JLabel("M = 2 ", SwingConstants.CENTER);
        label2.setFont(new Font("Serif", BOLD,40));
        JLabel label3 = new JLabel("Lungimea medie a combinatiilor de cod = " + lungimeMedie, SwingConstants.CENTER);
        label3.setFont(new Font("Serif", BOLD,40));
        JLabel label4 = new JLabel("Eficenta = " + eficenta, SwingConstants.CENTER);
        label4.setFont(new Font("Serif", BOLD,40));
        JLabel label5 = new JLabel("Redundanta = " + redundanta, SwingConstants.CENTER);
        label5.setFont(new Font("Serif", BOLD,40));
        JLabel label6 = new JLabel(entropie + "≤" + lungimeMedie + "<" + (entropie+1), SwingConstants.CENTER);
        label6.setFont(new Font("Serif", BOLD,40));
        JLabel label7 = new JLabel(" Teorema I a lui Shannon este verificata ", SwingConstants.CENTER);
        label7.setFont(new Font("Serif", BOLD,40));
        JLabel label8 = new JLabel("Numarul total de caractere = " + total, SwingConstants.CENTER);
        label8.setFont(new Font("Serif", BOLD,40));


        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);

        frame1.add(panel);
        // Frame Size
        frame1.setSize(1200, 600);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Frame Visible = true
        frame1.setVisible(true);
    }

}
