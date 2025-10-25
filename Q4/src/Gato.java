package Q4.src;

public class Gato extends Mamifero implements Domestico{
    private String raca;

    public Gato (String nome, int idade, String raca){
        super(nome, idade);
        this.raca = raca;
    }

    public void ronronar(){
        System.out.println(nome + "(GATO) - ronronando | raça: " + raca);
    }

    @Override
    public void fazerSom(){
        System.out.println(nome + "(GATO) - miando");
    }

    @Override
    public void brincar(){
        System.out.println(nome + "(DOMESTICO) - brincando");
    }
}
