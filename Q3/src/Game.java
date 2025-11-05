package Q3.src;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> jogadoresDaRodada;
    private Dado dado1;
    private Dado dado2;
    private Ranking rankingManager;
    private Scanner scanner;

    public Game() {
        this.jogadoresDaRodada = new ArrayList<>();
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        this.rankingManager = new Ranking();
        this.scanner = new Scanner(System.in);
    }

    public void iniciarJogo() {
        mostrarTelaInicial();

        coletarJogadores();

        if (jogadoresDaRodada.isEmpty()) {
            System.out.println("Nenhum jogador adicionado | Encerrando jogo. . .");
            return;
        }
        
        System.out.println("\n---Jogadores prontos!---");
        System.out.println("Lançando dados. .. ");
        
        int numeroSorteado = rolarDados();

        verificarVencedores(numeroSorteado);

        System.out.println("\n-> FIM DE JOGO!");
    }

    private void mostrarTelaInicial() {
        rankingManager.exibirTopFive();
        System.out.println("\nPressione [Enter] para iniciar o jogo e cadastrar jogadores. . .");
        scanner.nextLine();
    }

    private void coletarJogadores() {
        HashSet<String> usernamesRegistrados = new HashSet<>();
        
        System.out.println("---Cadastro de jogadores---");
        
        while (jogadoresDaRodada.size() < 11) {
            System.out.println("\nJogador " + (jogadoresDaRodada.size() + 1) + " (máx: 11)");
            
            String username;
            while (true) {
                System.out.print("Digite o 'user': ");
                username = scanner.nextLine();
                if (username.trim().isEmpty()) {
                    System.out.println("Erro: Username não pode ser vazio.");
                } else if (usernamesRegistrados.contains(username.toLowerCase())) {
                    System.out.println("Erro: Username já existe nesta rodada.");
                } else {
                    usernamesRegistrados.add(username.toLowerCase());
                    break;
                }
            }

            int aposta = 0;
            while (true) {
                try {
                    System.out.print("Valor da aposta (2 a 12): ");
                    aposta = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (aposta >= 2 && aposta <= 12) {
                        break;
                    } else {
                        System.out.println("Erro: Aposta deve ser um número entre 2 e 12.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Entrada inválida.");
                    scanner.nextLine();
                }
            }

            jogadoresDaRodada.add(new Player(username, aposta));
            System.out.println("Jogador '" + username + "' adicionado com aposta no número " + aposta + ".");

            if (jogadoresDaRodada.size() < 11) {
                System.out.print("\nDeseja adicionar outro jogador? (S/N): ");
                String continuar = scanner.nextLine();
                if (!continuar.equalsIgnoreCase("S")) {
                    break;
                }
            } else {
                System.out.println("Limite de 11 jogadores atingido.");
            }
        }
    }

    private int rolarDados() {
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        
        int valorDado1 = dado1.rolar();
        System.out.println("Dado 1 rolou: " + valorDado1);
        
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        int valorDado2 = dado2.rolar();
        System.out.println("Dado 2 rolou: " + valorDado2);
        
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        int soma = valorDado1 + valorDado2;
        System.out.println("\nNúmero sorteado: " + soma);
        System.out.println("------------------------");
        return soma;
    }

    private void verificarVencedores(int numeroSorteado) {
        ArrayList<Player> vencedores = new ArrayList<>();

        for (Player p : jogadoresDaRodada) {
            if (p.getAposta() == numeroSorteado) {
                vencedores.add(p);
            }
        }

        if (vencedores.isEmpty()) {
            System.out.println("Nenhum jogador acertou.");
            System.out.println("Computador venceu!");
        } else {
            System.out.println("-> Vencedores");
            
            for (Player vencedor : vencedores) {
                System.out.println("- " + vencedor.getUsername() + " (apostou " + vencedor.getAposta() + ")");
                rankingManager.atualizarVitorias(vencedor);
            }
        }
    }
}