package Questao_2;
import java.util.Scanner;

public class PrincipalRobo{ 
    private static final int TAMANHO_LINHA = 20;
    private static final int TAMANHO_COLUNA = 40;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        // posição robô 0,0 (posição inicial do robô)
        Robo R1 = new Robo(0, 0, 1);

        int opcao = -1;

        while (opcao != 0){
            desenharSala(R1);
            
            R1.mostrarPosicaoAtual();
            System.out.println("\n--- Controle do Robô ---");
            System.out.println("1 - Andar para frente (baixo)");
            System.out.println("2 - Andar para trás (cima)");
            System.out.println("3 - Andar para direita");
            System.out.println("4 - Andar para esquerda");
            System.out.println("0 - Sair");
            System.out.print("Escolha seu movimento: ");

            try{
                opcao = scanner.nextInt();
                
                switch (opcao){
                    case 1:
                        R1.andarFrente(TAMANHO_LINHA - 1);
                        break;
                    case 2:
                        R1.andarTras();
                        break;
                    case 3:
                        R1.andarDireita(TAMANHO_COLUNA - 1);
                        break;
                    case 4:
                        R1.andarEsquerda();
                        break;
                    case 0:
                        System.out.println("Desligando robô. . .");
                        break;
                    default:
                        System.out.println("Entrada inválida.");
                }
            } catch (Exception e){
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.next(); 
            }
            System.out.println(); 
        }
        scanner.close();
    }

    private static void desenharSala(Robo robo){
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        // delimitando a sala (borda superior)
        System.out.print("+");
        for (int j = 0; j < (TAMANHO_COLUNA * 2); j++){ 
            System.out.print("-");
        }
        System.out.println("+"); 

        // delimitando a sala (bordas laterais)
        for (int i = 0; i < TAMANHO_LINHA; i++){
            System.out.print("|"); 
            
            for (int j = 0; j < TAMANHO_COLUNA; j++){
                if (i == robo.getLinha() && j == robo.getColuna()) {
                    System.out.print("1 "); 
                } else{
                    System.out.print("  "); 
                }
            }
            
            System.out.println("|");
        }
    
        // delimitando a sala (borda inferior)
        System.out.print("+"); 
        for (int j = 0; j < (TAMANHO_COLUNA * 2); j++){ 
            System.out.print("-");
        }
        System.out.println("+"); 
    }
}