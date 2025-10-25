package Q4.src;

public class Ave extends Animal implements Domestico, Voador {
    
    private boolean deRapina;

    public Ave (String nome, boolean deRapina){
        super(nome);
        this.deRapina = deRapina;
    }

    public void bicar(){
        System.out.println(nome + "(AVE) - bicando");
        if(this.deRapina){
            System.out.println(nome + "é uma ave de rapina");
        }
    }

    @Override
    public void fazerSom(){
        System.out.println(nome + "(AVE) - fez som");
    }

    @Override
    public void brincar(){
        System.out.println("(DOMESTICO) - brincando");
    }

    @Override
    public void voar(){
        System.out.println(nome + "(VOADOR) - voando");
    }

    @Override
    public void aterrissar(){
        System.out.println(nome + "(VOADOR) - aterrissou");
    }
}