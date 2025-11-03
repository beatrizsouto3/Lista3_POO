package Q4.src;
public class Principal {
    public static void main (String[] args){

        System.out.println("---Testando mamifero (mamifero) ---");
        Mamifero mamifero = new Mamifero("Baleia", 3);
        mamifero.comer();
        mamifero.amamentar();
        mamifero.fazerSom();
        
        System.out.println("\n---Testando ave (papagaio)---");
        Ave papagaio = new Ave("Papagaio", false);
        papagaio.comer();
        papagaio.bicar();
        papagaio.fazerSom();
        papagaio.brincar();
        papagaio.voar();
        papagaio.aterrissar();

        System.out.println("\n---Testando cachorro---");

        System.out.println("Testando viraLata");
        Cachorro viraLata = new Cachorro("Vira lata", 4, 4.47); 
        viraLata.comer();
        viraLata.amamentar();
        viraLata.abanarRabo();
        viraLata.fazerSom();
        viraLata.buscar();
        viraLata.buscar("osso");
        
        System.out.println("\n---Testando golden---");
        Cachorro golden = new Cachorro("Golden", 1, 30.5); 
        golden.comer();
        golden.amamentar();
        golden.abanarRabo();
        golden.fazerSom();
        golden.buscar("osso");

        System.out.println("\n---Testando gato (siames)---");
        Gato siames = new Gato("Siames", 5, "Siamês");
        siames.comer();
        siames.amamentar();
        siames.ronronar();
        siames.fazerSom();
        siames.brincar();
    }
}