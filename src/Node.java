public class Node {
    Cliente cliente;
    Node left;
    Node right;

    public Node(Cliente cliente) {
        this.cliente = cliente;
        this.left = null;
        this.right = null;
    }
}