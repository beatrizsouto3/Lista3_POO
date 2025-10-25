package Q4.src;
public class Principal {
    public static void main (String[] args){

        System.out.println("--- Testando Mamifero (mamifero) ---");
        // (Req. I) Objeto de Mamifero (C2)
        Mamifero mamifero = new Mamifero("Baleia", 3);
        mamifero.comer();
        mamifero.amamentar();
        mamifero.fazerSom();
        
        System.out.println("\n--- Testando Ave (papagaio) ---");
        Ave papagaio = new Ave("Papagaio", false);
        papagaio.comer();
        papagaio.bicar();
        papagaio.fazerSom();
        papagaio.brincar();
        papagaio.voar();
        papagaio.aterrissar();

        System.out.println("\n--- Testando Cachorro ---");

        System.out.println("Testando viraLata");
        Cachorro viraLata = new Cachorro("Vira Lata", 4, 4.47); 
        viraLata.comer();
        viraLata.amamentar();
        viraLata.abanarRabo();
        viraLata.fazerSom();
        viraLata.buscar();
        viraLata.buscar("osso");
        
        System.out.println("\nTestando golden");
        Cachorro golden = new Cachorro("Golden", 1, 30.5); 
        golden.comer();
        golden.amamentar();
        golden.abanarRabo();
        golden.fazerSom();
        golden.buscar("osso");

        System.out.println("\n--- Testando Gato (siames) ---");
        Gato siames = new Gato("Siames", 5, "Siamês");
        siames.comer();
        siames.amamentar();
        siames.ronronar();
        siames.fazerSom();
        siames.brincar();
    }
}