import java.util.Scanner;
import java.util.Random; // Para simular aceitação da oferta


public class DivulgaOferta {
    /*
     * Tony Khaled Osman - RM553050
     * Bruno Salge Figueira - RM552859
     * Lucas Fontes Peruzin - RM552877
     */

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random random = new Random(); // Para simular se cliente aceita ou não

        AbbCliente cadastro = new AbbCliente();
        AbbCliente oferta = new AbbCliente();
        FilaCliente filaOferta = new FilaCliente();
        filaOferta.init();

        int opcao, op;
        String nome, whatsapp, cpf;
        double totalGasto;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println(" 0 - Encerrar o programa");
            System.out.println(" 1 - Inscricao um cliente");
            System.out.println(" 2 - Oferta de novo produto/promocao");
            System.out.println(" 3 - Entrar no Submenu de Consultas");
            System.out.println(" 4 - Remove um cliente do cadastro");
            System.out.print("Escolha uma opcao: ");
            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 0:
                    System.out.println("\n\n--- Clientes Aptos para Oferta (Não contatados ou recusaram) ---");
                    cadastro.apresentarNaoAceitaramOferta(cadastro.root);
                    System.out.println("\nEncerrando o programa...");
                    break;
                case 1:
                    System.out.println("\n--- Inscricao de Cliente ---");
                    System.out.print("Digite nome: ");
                    nome = entrada.nextLine();
                    System.out.print("Digite CPF (apenas numeros): ");
                    cpf = entrada.next();
                    System.out.print("Whatsapp: ");
                    whatsapp = entrada.next();
                    System.out.print("Informe total gasto do cliente R$: ");
                    totalGasto = entrada.nextDouble();
                    entrada.nextLine();

                    Cliente novoCliente = new Cliente(nome, cpf, whatsapp, totalGasto);
                    cadastro.root = cadastro.inserirPorCPF(cadastro.root, novoCliente); // Insere na ABB por CPF
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("\n--- Gerar Oferta ---");
                    System.out.print("Qual o valor de gasto minimo exigido para a oferta? R$ ");
                    double valorMinimoOferta = entrada.nextDouble();
                    entrada.nextLine();


                    oferta.esvaziar();
                    cadastro.gerarAbbOferta(cadastro.root, oferta, valorMinimoOferta);

                    if (oferta.root == null) {
                        System.out.println("Nenhum cliente apto encontrado para esta oferta com gasto minimo de R$ " + valorMinimoOferta);
                        break;
                    }


                    filaOferta.init();
                    oferta.gerarFilaOfertaDecrescente(oferta.root, filaOferta);


                    oferta.esvaziar();


                    System.out.println("\n--- Iniciando Contato com Clientes da Fila ---");
                    while (!filaOferta.isEmpty()) {
                        Cliente clienteParaContato = filaOferta.dequeue();
                        System.out.println("\nContatando cliente: " + clienteParaContato.nome + " (CPF: " + clienteParaContato.cpf + ", Gasto: R$ " + clienteParaContato.totalGasto + ")");

                        // Simula se o cliente aceitou a oferta
                        boolean aceitou = random.nextBoolean();

                        if (aceitou) {
                            System.out.println(">>> Cliente ACEITOU a oferta!");
                            boolean atualizado = cadastro.atualizarStatusOferta(cadastro.root, clienteParaContato.getCpf(), false);
                            if (!atualizado) {
                                System.out.println("!!! Erro ao atualizar status do cliente no cadastro.");
                            }
                        } else {
                            System.out.println(">>> Cliente RECUSOU a oferta.");
                        }
                    }
                    System.out.println("\n--- Fim dos Contatos da Fila ---");
                    break;

                case 3:
                    do {
                        System.out.println("\n--- SUBMENU CONSULTAS ---");
                        System.out.println("\t 1) Consulta cliente buscando pelo CPF");
                        System.out.println("\t 2) Apresenta o total de gasto de todos os clientes");
                        System.out.println("\t 3) Apresenta a quantidade de clientes com gasto acima de um valor");
                        System.out.println("\t 4) Voltar menu principal");
                        System.out.print("\t Escolha uma opcao do submenu: ");
                        op = entrada.nextInt();
                        entrada.nextLine();

                        switch (op) {
                            case 1:
                                System.out.print("\t Informe CPF para consulta: ");
                                cpf = entrada.next();
                                entrada.nextLine();
                                Cliente clienteConsultado = cadastro.consultarPorCPF(cadastro.root, cpf);
                                if (clienteConsultado != null) {
                                    System.out.println("\t --- Dados do Cliente ---");
                                    clienteConsultado.apresentarDados();
                                } else {
                                    System.out.println("\t CPF nao encontrado no cadastro.");
                                }
                                break;
                            case 2:
                                double somaTotal = cadastro.somarGastos(cadastro.root);
                                System.out.printf("\t Soma total dos gastos de todos os clientes: R$ %.2f\n", somaTotal);
                                break;
                            case 3:
                                System.out.print("\t Qual valor minimo de gastos para consulta? R$ ");
                                double minimoConsulta = entrada.nextDouble();
                                entrada.nextLine();
                                int qtdClientes = cadastro.contarClientesAcimaGasto(cadastro.root, minimoConsulta);
                                System.out.println("\t Quantidade de clientes com gastos acima de R$ " + minimoConsulta + ": " + qtdClientes);
                                break;
                            case 4:
                                System.out.println("\t Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("\t Opcao invalida no submenu!");
                        }
                    } while (op != 4);
                    break;

                case 4:
                    System.out.println("\n--- Remover Cliente do Cadastro ---");
                    System.out.print("Informe CPF do cliente que deseja remover: ");
                    cpf = entrada.next();
                    entrada.nextLine();

                    Cliente clienteParaRemover = cadastro.consultarPorCPF(cadastro.root, cpf);
                    if (clienteParaRemover != null) {
                        cadastro.root = cadastro.removerPorCPF(cadastro.root, cpf);
                        System.out.println("Cliente com CPF " + cpf + " removido com sucesso (se encontrado).");
                    } else {
                        System.out.println("Cliente com CPF " + cpf + " nao encontrado para remocao.");
                    }
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);

        entrada.close();
    }
}