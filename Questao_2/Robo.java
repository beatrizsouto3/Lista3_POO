package Questao_2;
public class Robo{
    private int linha;
    private int coluna;
    private int passo;

    public Robo(int linhaInicial, int colunaInicial, int passo){
        this.linha = linhaInicial;
        this.coluna = colunaInicial;
        this.passo = passo;
    }

    public void mostrarPosicaoAtual(){
        System.out.println("\nPosição do robô - Linha: " + this.linha + ", Coluna: " + this.coluna + ". ");
    }

    public void andarFrente(int maxLinha){
        int novaLinha = this.linha + this.passo;
        
        // limitação da sala
        if (novaLinha > maxLinha){
            this.linha = maxLinha; 
            System.out.println("O robô atingiu o limite da sala!!! Escolha outra direção.");
        } else{
            this.linha = novaLinha;
        }
    }

    public void andarTras(){
        int novaLinha = this.linha - this.passo;
        
        if (novaLinha < 0){
            this.linha = 0;
            System.out.println("O robô atingiu o limite da sala!!! Escolha outra direção.");
        } else{
            this.linha = novaLinha;
        }
    }
    
    public void andarDireita(int maxColuna){
        int novaColuna = this.coluna + this.passo;
        
        if (novaColuna > maxColuna){
            this.coluna = maxColuna;
            System.out.println("O robô atingiu o limite da sala!!! Escolha outra direção.");
        } else{
            this.coluna = novaColuna;
        }
    }

    public void andarEsquerda(){
        int novaColuna = this.coluna - this.passo;
        
        if (novaColuna < 0){
            this.coluna = 0;
            System.out.println("O robô atingiu o limite da sala!!! Escolha outra direção.");
        } else{
            this.coluna = novaColuna;
        }
    }

    public int getLinha(){
        return linha;
    }

    public int getColuna(){
        return coluna;
    }
}