package Q4.src;
public abstract class Animal {
    protected String nome;

    public Animal (String nome){
        this.nome = nome;
    }

    public void comer(){
        System.out.println(nome + "(ANIMAL) - comendo");
    }

    public abstract void fazerSom();
}
