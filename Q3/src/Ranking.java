package Q3.src;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Ranking {
    private static final String NOME_ARQUIVO = "ranking_jogo_dados.txt";

    public void exibirTopFive() {
        Map<String, Integer> ranking = lerRanking();

        if (ranking.isEmpty()) {
            System.out.println("|| Ranking vazio. ||");
            return;
        }

        System.out.println("---RANKING TOP 5---");
        Map<String, Integer> rankingOrdenado = ranking.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        int pos = 1;
        for (Map.Entry<String, Integer> entry : rankingOrdenado.entrySet()) {
            String nome = entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
            System.out.println(pos + ". " + nome + " - " + entry.getValue() + " vitórias");
            pos++;
        }
        System.out.println("------------------------");
    }

    public void atualizarVitorias(Player player) {
        String usernameKey = player.getUsername().toLowerCase(); 
        Map<String, Integer> ranking = lerRanking();

        int vitoriasAtuais = ranking.getOrDefault(usernameKey, 0);
        ranking.put(usernameKey, vitoriasAtuais + 1);

        salvarRanking(ranking);
    }

    private Map<String, Integer> lerRanking() {
        Map<String, Integer> ranking = new HashMap<>();
        File file = new File(NOME_ARQUIVO);

        if (!file.exists()) {
            return ranking;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(":");
                if (partes.length == 2) {
                    try {
                        ranking.put(partes[0].toLowerCase(), Integer.parseInt(partes[1]));
                    } catch (NumberFormatException e) {
                        System.err.println("Linha mal formatada no ranking: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler ranking: " + e.getMessage());
        }
        return ranking;
    }

    private void salvarRanking(Map<String, Integer> ranking) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO, false))) {
            for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
                writer.println(entry.getKey().toLowerCase() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar ranking: " + e.getMessage());
        }
    }
}