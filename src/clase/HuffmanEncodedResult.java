package clase;

public class HuffmanEncodedResult {
    final Node root;
    final String encodedData;

    public HuffmanEncodedResult(final Node root, final String encodedData) {
        this.root = root;
        this.encodedData = encodedData;
    }

    public Node getRoot() {
        return root;
    }

    public String getEncodedData() {
        return encodedData;
    }

}
