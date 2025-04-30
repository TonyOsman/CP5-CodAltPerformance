# CP5 - ABB - Códigos de Alta Performance
---
#### Integrantes do Grupo:
* Tony Khaled Osman - RM553050
* Bruno Salge Figueira - RM552859
* Lucas Fontes Peruzin - RM552877
* Pedro Hendrique lima - RM553664
* Luiz Fabiano Nascimento Vale da Silva - RM553529
  

## Descrição:

Este sistema em Java tem como objetivo priorizar o contato de uma loja de cosméticos com seus clientes para oferecer novas ofertas especiais.  O sistema permite o cadastro de clientes, a seleção de clientes aptos para ofertas com base em seus gastos, a geração de uma fila de clientes para contato e o registro das interações com os clientes.    

## Funcionalidades:

O sistema oferece as seguintes funcionalidades através de um menu principal:

1.  **Inscrição de Cliente:** Permite ao gerente de relacionamento cadastrar novos clientes no sistema, incluindo nome, CPF, Whatsapp e valor total em compras. Os registros são armazenados em uma Árvore de Busca Binária (ABB) organizada pelo CPF do cliente.    

2.  **Oferta de Novo Produto/Promoção:** Permite gerar uma nova ABB (ABB de oferta) contendo apenas clientes com gastos acima de um valor mínimo definido.  A partir dessa ABB de oferta, é gerada uma fila de clientes para contato em ordem decrescente de total de gastos.  O sistema simula o contato com os clientes da fila, registrando se aceitaram ou não a oferta.    

3.  **Menu de Consulta:** Oferece um submenu com as seguintes opções de consulta na ABB de cadastro:
- Consulta de cliente por CPF.    
- Somatório dos gastos de todos os clientes.    
- Quantidade de clientes com gastos acima de um valor consultado.    
- Encerra o Programa: Apresenta todos os clientes que não aceitaram nenhuma das ofertas antes de encerrar a aplicação.    

## Estrutura do Código:

O código Java é organizado nas seguintes classes principais:

- **DivulgaOferta:** Classe principal que contém o método main e implementa o menu da aplicação.    
- **Cliente:** Classe que representa um cliente da loja, contendo atributos como nome, CPF, Whatsapp, valor total em compras e um indicador se está apto para oferta.    
- **AbbCliente**: Classe que implementa a Árvore de Busca Binária (ABB) para armazenar os clientes.    
- **FilaCliente**: Classe que implementa a fila de clientes para contato.    


## Como Executar:

1. Certifique-se de ter o Java Development Kit (JDK) instalado.
2. Compile os arquivos Java.
3. Execute a classe DivulgaOferta.


## Requisitos:

- Java Development Kit (JDK)

---

***Os nomes e RMs dos componentes do grupo estão inclusos como comentário no código fonte da classe DivulgaOferta.***   



