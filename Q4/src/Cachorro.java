package Q4.src;
public class Cachorro extends Mamifero {
    private double peso;

    public Cachorro(String nome, int idade){
        super(nome, idade);
        this.peso = 0.0;
    }

    public Cachorro(String nome, int idade, double peso){
        super(nome, idade);
        this.peso = peso;
    }

    public void abanarRabo(){
        System.out.println(nome + "(CACHORRO) - abanou o rabo | peso : " + this.peso + "kg");
    }

    public void buscar(){
        System.out.println(nome + "(CACHORRO) - buscou");
    }

    public void buscar(String objeto){
        System.out.println(nome + "(CACHORRO) - buscou o " + objeto);
    }

    @Override
    public void fazerSom(){
        System.out.println(nome + "(CACHORRO) - latiu");
    }
}
