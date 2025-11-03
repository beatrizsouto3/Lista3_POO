package Q3.src;

public class Player {
    private String username;
    private int aposta;

    public Player(String username, int aposta){
        this.username = username;
        this.aposta = aposta;
    }

    public String getUsername(){
        return username;
    }

    public int getAposta(){
        return aposta;
    }
}
