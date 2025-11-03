import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Principal {

    private static ArrayList<Pedido> listaPedidos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    private static int proximoCodigoPedido = 1;
    
    private static final double PRECO_BOTIJAO = 110.00; 

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        fazerPedido();
                        break;
                    case 2:
                        confirmarEntrega();
                        break;
                    case 3:
                        verPedidosPorStatus(StatusPedido.CONFIRMADO);
                        break;
                    case 4:
                        verPedidosPorStatus(StatusPedido.ENTREGUE);
                        break;
                    case 0:
                        System.out.println("Encerrando Atendimento . . .");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
            }
            if (opcao != 0) {
                System.out.println("\n(Pressione Enter para continuar...)");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n---Loja Gás 24h---");
        System.out.println("1. Fazer pedido");
        System.out.println("2. Confirmar entrega de pedido");
        System.out.println("3. Ver pedidos confirmados (aguardando entrega)");
        System.out.println("4. Ver pedidos entregues (concluídos)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void fazerPedido() {
        System.out.println("---Novo Pedido---");
        try {
            System.out.println("Insira a data e hora da compra (ex: 02/11/2025 14:30):");
            System.out.print("Data - (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            System.out.print("Hora - (HH:mm): ");
            String horaStr = scanner.nextLine();
            
            DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHoraCompra = LocalDateTime.parse(dataStr + " " + horaStr, formatadorData);

            System.out.print("Endereço de entrega: ");
            String endereco = scanner.nextLine();

            System.out.print("Quantidade inicial de botijões: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            
            Pedido novoPedido = new Pedido(proximoCodigoPedido, dataHoraCompra, endereco, quantidade);

            
            boolean confirmado = false;
            while (!confirmado) {
                System.out.println("\n-Revise seu pedido");
                System.out.println(novoPedido);
                System.out.print("Digite (C) para Confirmar ou (A) para Alterar o endereço: ");
                String escolha = scanner.nextLine().toUpperCase();

                if (escolha.equals("A")) {
                    System.out.print("Digite o NOVO endereço de entrega: ");
                    String novoEndereco = scanner.nextLine();
                    novoPedido.setEnderecoEntrega(novoEndereco);
                    System.out.println("Endereço alterado!");
                } else if (escolha.equals("C")) {
                    System.out.print("Confirme a quantidade final de botijões: ");
                    int qtdFinal = scanner.nextInt();
                    scanner.nextLine();
                    novoPedido.setQuantidadeBotijoes(qtdFinal);
                    
                    confirmado = true;
                } else {
                    System.out.println("Opção inválida.");
                }
            }
            
            System.out.println(String.format("\nO preço unitário do botijão é R$ %.2f", PRECO_BOTIJAO));
            novoPedido.calcularValorTotal(PRECO_BOTIJAO);
            novoPedido.calcularHoraEntrega();
            
            System.out.println("-> Dados finais (valor e entrega)");
            System.out.println(novoPedido);

            System.out.print("Digite o número do Cartão de crédito para pagamento: ");
            String cartao = scanner.nextLine();
            novoPedido.confirmarPagamento(cartao);
            
            System.out.println("\nPagamento aprovado. Pedido CONFIRMADO!");
            System.out.println("O CÓDIGO do seu pedido é: " + novoPedido.getCodigoPedido());

            listaPedidos.add(novoPedido);
            proximoCodigoPedido++;

        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada de dados inválida. O pedido foi cancelado.");
            scanner.nextLine();
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Formato de data ou hora inválido (Use dd/MM/yyyy e HH:mm). O pedido foi cancelado.");
        }
    }

    private static void confirmarEntrega() {
        System.out.println("---Confirmar entrega---");
        System.out.print("Digite o código do pedido: ");
        
        try {
            int codigo = scanner.nextInt();
            scanner.nextLine();

            Pedido pedido = buscarPedidoPorCodigo(codigo);

            if (pedido == null) {
                System.out.println("Erro: Pedido não localizado.");
            } else if (pedido.getStatus() == StatusPedido.CONFIRMADO) {
                pedido.confirmarEntrega();
                System.out.println("Sucesso: Pedido " + codigo + " foi marcado como ENTREGUE.");
            } else if (pedido.getStatus() == StatusPedido.ENTREGUE) {
                System.out.println("Aviso: pedido já entregue.");
            } else {
                System.out.println("Aviso: pedido ainda não confirmado/pago (status: EM_ABERTO).");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Código inválido. Digite um número.");
            scanner.nextLine();
        }
    }

    private static void verPedidosPorStatus(StatusPedido status) {
        System.out.println("---Pedidos com status: " + status + "---");
        boolean encontrados = false;
        
        for (Pedido p : listaPedidos) {
            if (p.getStatus() == status) {
                System.out.println(p);
                encontrados = true;
            }
        }

        if (!encontrados) {
            System.out.println("Nenhum pedido encontrado com este status.");
        }
    }

    private static Pedido buscarPedidoPorCodigo(int codigo) {
        for (Pedido p : listaPedidos) {
            if (p.getCodigoPedido() == codigo) {
                return p;
            }
        }
        return null;
    }
}