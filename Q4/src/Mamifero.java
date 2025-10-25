package Q4.src;
public class Mamifero extends Animal{
    protected int idade;

    public Mamifero(String nome, int idade){
        super(nome);
        this.idade = idade;
    }

    public void amamentar(){
        System.out.println(nome + "(MAMIFERO) - amamentando");
    }

    @Override
    public void fazerSom(){
        System.out.println(nome + "(MAMIFERO) - fazendo som");
    }
}
