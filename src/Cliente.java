public class Cliente {
    String nome;
    String cpf;
    String whatsapp;
    double totalGasto;
    boolean aptoParaOferta;

    public Cliente(String nome, String cpf, String whatsapp, double totalGasto) {
        this.nome = nome;
        this.cpf = cpf;
        this.whatsapp = whatsapp;
        this.totalGasto = totalGasto;
        this.aptoParaOferta = true;
    }

    public void apresentarDados() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Whatsapp: " + whatsapp);
        System.out.printf("Total Gasto: R$ %.2f\n", totalGasto);
        System.out.println("Apto para Oferta: " + (aptoParaOferta ? "Sim" : "Não"));
        System.out.println("-------------------------");
    }

    public String getCpf() {
        return cpf;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public boolean isAptoParaOferta() {
        return aptoParaOferta;
    }

    public void setAptoParaOferta(boolean aptoParaOferta) {
        this.aptoParaOferta = aptoParaOferta;
    }

    @Override
    public String toString() {
        return "Cliente [nome=" + nome + ", cpf=" + cpf + ", whatsapp=" + whatsapp + ", totalGasto=" + totalGasto
                + ", aptoParaOferta=" + aptoParaOferta + "]";
    }
}