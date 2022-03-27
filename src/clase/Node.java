package clase;

public class Node implements Comparable<Node> {

    protected char litera;
    protected int frecventa;
    protected double probabilitate;
    protected String codHuff;
    protected Node stanga;
    protected Node dreapta;


    public Node(char litera, int frecventa,double probabilitate, String codHuff, Node stanga, Node dreapta) {
        this.litera = litera;
        this.frecventa = frecventa;
        this.probabilitate = probabilitate;
        this.codHuff = codHuff;
        this.stanga = stanga;
        this.dreapta = dreapta;
    }

    public Node(char litera, int frecventa, Node stanga, Node dreapta) {
        this.litera = litera;
        this.frecventa = frecventa;
        this.stanga = stanga;
        this.dreapta = dreapta;
    }

    public Node(){

    }

    public char getLitera() {
        return litera;
    }

    public void setLitera(char litera) {
        this.litera = litera;
    }

    public int getFrecventa() {
        return frecventa;
    }

    public void setFrecventa(int frecventa) {
        this.frecventa = frecventa;
    }

    public String getCodHuff() {
        return codHuff;
    }

    public void setCodHuff(String codHuff) {
        this.codHuff = codHuff;
    }

    public Node getStanga() {
        return stanga;
    }

    public void setStanga(Node stanga) {
        this.stanga = stanga;
    }

    public Node getDreapta() {
        return dreapta;
    }

    public void setDreapta(Node dreapta) {
        this.dreapta = dreapta;
    }

    public boolean isLeaf() {
        return this.stanga == null && this.dreapta == null;
    }

    @Override
    public int compareTo(Node o) {
        int frecventaComparare = Integer.compare(this.frecventa,o.frecventa);
        if(frecventaComparare != 0){
            return frecventaComparare;
        }
        return Integer.compare(this.frecventa,o.frecventa);
    }




}

