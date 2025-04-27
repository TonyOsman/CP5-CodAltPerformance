public class AbbCliente {
    Node root;

    public AbbCliente() {
        this.root = null;
    }

    public Node inserirPorCPF(Node node, Cliente cliente) {
        if (node == null) {
            return new Node(cliente);
        }

        if (cliente.getCpf().compareTo(node.cliente.getCpf()) < 0) {
            node.left = inserirPorCPF(node.left, cliente);
        } else if (cliente.getCpf().compareTo(node.cliente.getCpf()) > 0) {
            node.right = inserirPorCPF(node.right, cliente);
        } else {
            System.out.println("Cliente com CPF " + cliente.getCpf() + " já cadastrado.");
        }
        return node;
    }

    public Node inserirPorGasto(Node node, Cliente cliente) {
        if (node == null) {
            return new Node(cliente);
        }

        if (cliente.getTotalGasto() < node.cliente.getTotalGasto()) {
            node.left = inserirPorGasto(node.left, cliente);
        } else if (cliente.getTotalGasto() >= node.cliente.getTotalGasto()) {
            node.right = inserirPorGasto(node.right, cliente);
        }
        return node;
    }


    public Cliente consultarPorCPF(Node node, String cpf) {
        if (node == null) {
            return null;
        }

        int comparacao = cpf.compareTo(node.cliente.getCpf());

        if (comparacao < 0) {
            return consultarPorCPF(node.left, cpf);
        } else if (comparacao > 0) {
            return consultarPorCPF(node.right, cpf);
        } else {
            return node.cliente;
        }
    }

    public double somarGastos(Node node) {
        if (node == null) {
            return 0;
        }
        return node.cliente.getTotalGasto() + somarGastos(node.left) + somarGastos(node.right);
    }

    public int contarClientesAcimaGasto(Node node, double limite) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.cliente.getTotalGasto() >= limite) {
            count = 1;
        }
        return count + contarClientesAcimaGasto(node.left, limite) + contarClientesAcimaGasto(node.right, limite);
    }

    public boolean atualizarStatusOferta(Node node, String cpf, boolean apto) {
        Cliente cliente = consultarPorCPF(node, cpf);
        if (cliente != null) {
            cliente.setAptoParaOferta(apto);
            return true;
        }
        return false;
    }

    public void gerarAbbOferta(Node nodeCadastro, AbbCliente abbOferta, double limiteGasto) {
        if (nodeCadastro == null) {
            return;
        }

        gerarAbbOferta(nodeCadastro.left, abbOferta, limiteGasto);

        if (nodeCadastro.cliente.isAptoParaOferta() && nodeCadastro.cliente.getTotalGasto() >= limiteGasto) {
            Cliente clienteParaOferta = new Cliente(
                    nodeCadastro.cliente.nome,
                    nodeCadastro.cliente.cpf,
                    nodeCadastro.cliente.whatsapp,
                    nodeCadastro.cliente.totalGasto
            );
            clienteParaOferta.setAptoParaOferta(true);
            abbOferta.root = abbOferta.inserirPorGasto(abbOferta.root, clienteParaOferta);
        }

        gerarAbbOferta(nodeCadastro.right, abbOferta, limiteGasto);
    }

    public void gerarFilaOfertaDecrescente(Node nodeOferta, FilaCliente fila) {
        if (nodeOferta == null) {
            return;
        }
        gerarFilaOfertaDecrescente(nodeOferta.right, fila);
        fila.enqueue(nodeOferta.cliente);
        gerarFilaOfertaDecrescente(nodeOferta.left, fila);
    }

    public void esvaziar() {
        this.root = null;
    }

    private Node encontrarMinimo(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    public Node removerPorCPF(Node node, String cpf) {
        if (node == null) {
            return null;
        }

        int comparacao = cpf.compareTo(node.cliente.getCpf());

        if (comparacao < 0) {
            node.left = removerPorCPF(node.left, cpf);
        } else if (comparacao > 0) {
            node.right = removerPorCPF(node.right, cpf);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node temp = encontrarMinimo(node.right);
            node.cliente = temp.cliente;
            node.right = removerPorCPF(node.right, temp.cliente.getCpf());
        }
        return node;
    }

    public void apresentarNaoAceitaramOferta(Node node) {
        if (node == null) {
            return;
        }
        apresentarNaoAceitaramOferta(node.left);
        if (node.cliente.isAptoParaOferta()) {
            node.cliente.apresentarDados();
        }
        apresentarNaoAceitaramOferta(node.right);
    }
}