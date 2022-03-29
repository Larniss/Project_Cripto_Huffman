package com.company;

import clase.Functii;
import clase.GUI;
import clase.HuffmanEncodedResult;
import java.io.*;



public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Functii f1 = new Functii();

        File sursa = new File("D:\\GIT\\Project_Cripto_Huffman\\Legea_Nr_95");
        File sursa1 = new File("D:\\GIT\\Project_Cripto_Huffman\\Baltagul_Capitolul_I-X.txt");

        //citim textul eliminand spatiile
        f1.citireFisier(sursa1);


        double entropie = f1.calculEntropie();
        double lungime = f1.calculLungimeMedie();
        double eficienta = f1.calculEficienta();
        double redundanta = f1.calculRedundanta();
        int total = 0;
        for (int elem : f1.frecventaVector) {
            total += elem;
        }


        //initializam si afisam tabelul
        GUI gui1 = new GUI(f1.dateTabel());
        GUI gui2 = new GUI(entropie, lungime, eficienta, redundanta,total);


        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Partea de criptare decriptare(noua parte din proiect)
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        HuffmanEncodedResult encodeCeva = f1.compress(f1.citireSiProcesareText(sursa1));
        System.out.println(encodeCeva.getEncodedData());
        System.out.println(f1.decompress(encodeCeva));


    }

}


