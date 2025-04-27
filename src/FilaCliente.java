public class FilaCliente {

    private class NodeFila {
        Cliente cliente;
        NodeFila next;

        public NodeFila(Cliente cliente) {
            this.cliente = cliente;
            this.next = null;
        }
    }

    private NodeFila inicio;
    private NodeFila fim;


    public void init() {
        this.inicio = null;
        this.fim = null;
    }


    public boolean isEmpty() {
        return this.inicio == null;
    }

    public void enqueue(Cliente cliente) {
        NodeFila novoNode = new NodeFila(cliente);
        if (isEmpty()) {
            this.inicio = novoNode;
            this.fim = novoNode;
        } else {
            this.fim.next = novoNode;
            this.fim = novoNode;
        }
    }

    public Cliente dequeue() {
        if (isEmpty()) {
            System.out.println("Erro: Fila vazia!");
            return null;
        }


        Cliente clienteRemovido = this.inicio.cliente;


        this.inicio = this.inicio.next;


        if (this.inicio == null) {
            this.fim = null;
        }

        return clienteRemovido;
    }
}