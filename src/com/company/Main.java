package com.company;

import clase.Functii;
import clase.GUI;
import clase.HuffmanEncodedResult;
import java.io.*;



public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Functii f1 = new Functii();

        File sursa = new File("D:\\Facultate Info\\Anul 2\\Semestrul 2\\Criptografie\\Proiect_I\\Baltagul_Capitolul_I.txt");
        File sursa1 = new File("D:\\Facultate Info\\Anul 2\\Semestrul 2\\Criptografie\\Proiect_I\\Baltagul_Capitolul_I-X.txt");
        File sursa2 = new File("D:\\Facultate Info\\Anul 2\\Semestrul 2\\Criptografie\\Proiect_I\\incercare.txt");

        //citim textul eliminand spatiile
        f1.citireFisier(sursa);


        double entropie = f1.calculEntropie();
        double lungime = f1.calculLungimeMedie();
        double eficienta = f1.calculEficienta();
        double redundanta = f1.calculRedundanta();


        //initializam si afisam tabelul
        GUI gui1 = new GUI(f1.dateTabel());
        GUI gui2 = new GUI(entropie, lungime, eficienta, redundanta);


        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Partea de criptare decriptare(noua parte din proiect)
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        HuffmanEncodedResult encodeCeva = f1.compress(f1.citireSiProcesareText(sursa));
        System.out.println(encodeCeva.getEncodedData());
        System.out.println(f1.decompress(encodeCeva));

    }

}


