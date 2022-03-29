package clase;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

import static java.lang.String.valueOf;

public class Functii {
    //Sursele de text
    String sursa = "D:\\Facultate Info\\Anul 2\\Semestrul 2\\Criptografie\\Proiect_I\\Baltagul_Capitolul_I.txt";
    String sursa1 = "D:\\Facultate Info\\Anul 2\\Semestrul 2\\Criptografie\\Proiect_I\\Baltagul_Capitolul_I-X.txt";
    String sursa2 = "D:\\Facultate Info\\Anul 2\\Semestrul 2\\Criptografie\\Proiect_I\\incercare.txt";

    //Vectorul care contine toate literele alfabetului romanesc
    public final char[] alfabet = {'a', 'ă', 'â', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'î', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 'ș', 't', 'ț', 'u', 'v', 'w', 'x', 'y', 'z'};
    //Map care contine literele impreuna cu frecventele
    public Map<Character, Integer> literaFrecventa;
    public int[] frecventaVector = new int[alfabet.length]; //vectorul cu frecvente
    public double[] probabilitateVector = new double[alfabet.length]; //vectorul cu probabilitati
    public double entropie;
    public double lungimeMedie;
    public double eficienta;
    public double redundanta;

    //Variabilele pentru codul Huffman
    public Map<Character, String> lookup;


    public void initializareLinked() {
        literaFrecventa = new LinkedHashMap<>();
        for (char c : alfabet) {
            literaFrecventa.put(c, 0);
        }
    }

    // functia care proceseaza(ignora spatiile si punctuatia) din text
    public void procesareText(String sursa) {
        sursa = sursa.toLowerCase();

        for (int i = 0; i < sursa.length(); i++) {
            int pozitie = sursa.charAt(i);
            if (!Character.isLetter(pozitie)) {
                continue;
            }

            literaFrecventa.put(sursa.charAt(i), literaFrecventa.getOrDefault(sursa.charAt(i),0) + 1);
        }
    }

    //functia care citeste si introduce text intr-un String pentru a fi folosit de encode/decode
    public String citireSiProcesareText(File file) throws FileNotFoundException {
        Scanner scr = new Scanner(file);
        StringBuilder builder= new StringBuilder();
        initializareLinked();
        while (scr.hasNextLine()) {
            String data = scr.nextLine();
            data = data.toLowerCase();
            for (int i = 0; i < data.length(); i++) {
                int pozitie = data.charAt(i);
                if (!Character.isLetter(pozitie)) {
                    continue;
                }
                builder.append(data.charAt(i));
                }
            }
        return builder.toString();
    }

    public void citireFisier(File file) {
        //Citirea textului din fisier
        try {
            Scanner scr = new Scanner(file);
            initializareLinked();
            while (scr.hasNextLine()) {
                String data = scr.nextLine();
                procesareText(data);

            }
            scr.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //functia care introducem elementele din LinkedHashMap in doi vectori
    public void vectorFrecventa() {
        int i = 0;
        for (Map.Entry<Character, Integer> elem : literaFrecventa.entrySet()) {
            int value = elem.getValue();
            frecventaVector[i] = value;
            i++;
        }
    }

    // functia de calcul al probabilitatii de aparitie
    public void calculProbabilitate() {
        vectorFrecventa();
        int total = 0;
        for (int elem : frecventaVector) {
            total += elem;
        }
        for (int i = 0; i < 31; i++) {
            if (frecventaVector[i] == 0)
                continue;
            probabilitateVector[i] = (double) frecventaVector[i] / total;

        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Incep functiile pentru al doilea GUI
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //functia care calculeaza entropia
    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    public double calculEntropie() {
        vectorFrecventa();
        calculProbabilitate();
        entropie = 0;
        for (int i = 0; i < alfabet.length; i++) {
            if (frecventaVector[i] == 0)
                continue;
            entropie += probabilitateVector[i] * log2(1 / probabilitateVector[i]);
        }
        return entropie;
    }

    public double calculLungimeMedie() {
        lungimeMedie = 0;
        vectorFrecventa();
        Node nd = CreareArbore(frecventaVector, probabilitateVector);
        lookup = HashMapHuffman(nd);
        int total = 0;
        for (int elem : frecventaVector) {
            total += elem;
        }
        for (Character elem : literaFrecventa.keySet()) {
                if (literaFrecventa.get(elem) == 0)
                    continue;
                else
                    lungimeMedie += ((double) literaFrecventa.get(elem) / total) * lookup.get(elem).length();
            }

        return lungimeMedie;
    }

    public double calculEficienta() {
        eficienta = entropie / lungimeMedie;
        return eficienta;
    }

    public double calculRedundanta() {
        redundanta = 1 - eficienta;
        return redundanta;
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Incep Functiile pentru Codurile HUFFMAN
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    //functia care creaza arborele pentru Hoffman
    public Node CreareArbore(int[] frecventaVector, double[] probabilitateVector) {
        final PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for (char i = 0; i < alfabet.length; i++) {
            if (frecventaVector[i] > 0) {
                priorityQueue.add(new Node(alfabet[i],
                        frecventaVector[i],
                        probabilitateVector[i],
                        " ",
                        null,
                        null));
            }
        }
        if (priorityQueue.size() == 1) {
            priorityQueue.add(new Node('*', 1, null, null));
        }

        while (priorityQueue.size() > 1) {
            final Node stanga = priorityQueue.poll();
            final Node dreapta = priorityQueue.poll();
            final Node parinte = new Node('*', stanga.frecventa + dreapta.frecventa, stanga, dreapta);
            priorityQueue.add(parinte);
        }

        return priorityQueue.poll();
    }


    public Map<Character, String> HashMapHuffman(Node root) {
        Map<Character, String> HashMapHuff = new HashMap<>();
        creazaVectotHuffman(root, "", HashMapHuff);
        return HashMapHuff;
    }

    private void creazaVectotHuffman(Node root,
                                     String s,
                                     Map<Character, String> vectorHuff) {
        if (!root.isLeaf()) {
            creazaVectotHuffman(root.stanga, s + "0", vectorHuff);
            creazaVectotHuffman(root.dreapta, s + "1", vectorHuff);
        } else {
            vectorHuff.put(root.litera, s);
        }
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Partea de criptare decriptare(noua parte din proiect)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public HuffmanEncodedResult compress(String data) {
        procesareText(data);
        final Node root = CreareArbore(frecventaVector, probabilitateVector);
        final Map<Character, String> vectorHuff = HashMapHuffman(root);
        return new HuffmanEncodedResult(root, generateEncodedData(data,vectorHuff));
    }

    private String generateEncodedData(String data, Map<Character, String> vectorHuff) {
        StringBuilder builder = new StringBuilder();
        for(char caracter : data.toCharArray()){
            builder.append(vectorHuff.get(caracter));
        }
        return builder.toString();
    }

    public String decompress (final HuffmanEncodedResult result){
        final StringBuilder resultbuilder = new StringBuilder();

        Node current = result.getRoot();
        int i = 0;

        while(i < result.getEncodedData().length()){
            while(!current.isLeaf()){
                char bit = result.getEncodedData().charAt(i);
                if(bit == '1'){
                    current = current.dreapta;
                }else if(bit == '0'){
                    current = current.stanga;
                }else{
                    throw new IllegalArgumentException("Caracterul este invalid " + bit);
                }
                i++;
            }
            resultbuilder.append(current.litera);
            current = result.getRoot();
        }
        return resultbuilder.toString();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Se creaza matricea necesara afisarii
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //functia care introduce in matrice datele
    public String[][] dateTabel() {

        String[][] data = new String[31][4];
        vectorFrecventa();
        calculProbabilitate();
        Node nd = CreareArbore(frecventaVector, probabilitateVector);
        lookup = HashMapHuffman(nd);


        for (int i = 0; i < 31; i++) {
            data[i][0] = valueOf(alfabet[i]); // vector cu litere
            data[i][1] = valueOf(frecventaVector[i]); // vector cu aparitii
            data[i][2] = valueOf(probabilitateVector[i]); // vector cu probabilitate
            for (Character k : lookup.keySet()) {
                if (alfabet[i] == k) {
                    data[i][3] = valueOf(lookup.get(k)); // vector cu Huffman
                }
            }

        }

        return data;
    }
}



