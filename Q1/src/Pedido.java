import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Pedido{
    private int codigoPedido;
    private LocalDateTime dataHoraCompra;
    private String enderecoEntrega;
    private int quantidadeBotijoes;
    private double valorTotal;
    private LocalDateTime dataHoraEntregaPrevista;
    private String cartaoCredito;
    private StatusPedido status;
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

    public Pedido(int codigoPedido, LocalDateTime dataHoraCompra, String enderecoEntrega, int quantidadeBotijoes){
        this.codigoPedido = codigoPedido;
        this.dataHoraCompra = dataHoraCompra;
        this.enderecoEntrega = enderecoEntrega;
        this.quantidadeBotijoes = quantidadeBotijoes;
        this.status = StatusPedido.EM_ABERTO;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }
    
    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public void setQuantidadeBotijoes(int quantidadeBotijoes) {
        this.quantidadeBotijoes = quantidadeBotijoes;
    }
    
    public void calcularValorTotal(double precoUnitario) {
        this.valorTotal = this.quantidadeBotijoes * precoUnitario;
    }

    public void calcularHoraEntrega() {
        this.dataHoraEntregaPrevista = this.dataHoraCompra.plusHours(2);
    }

    public void confirmarPagamento(String cartao) {
        this.cartaoCredito = cartao; // Armazena o cartão (não seguro na vida real)
        this.status = StatusPedido.CONFIRMADO;
    }

    public void confirmarEntrega() {
        if (this.status == StatusPedido.CONFIRMADO) {
            this.status = StatusPedido.ENTREGUE;
        }
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-> Código do pedido: ").append(codigoPedido).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Data/Hora Compra: ").append(dataHoraCompra.format(FORMATADOR)).append("\n");
        sb.append("Endereço Entrega: ").append(enderecoEntrega).append("\n");
        sb.append("Quantidade: ").append(quantidadeBotijoes).append(" botijões\n");

        if (valorTotal > 0) {
            sb.append(String.format("Valor Total: R$ %.2f\n", valorTotal));
        }
        if (dataHoraEntregaPrevista != null) {
            sb.append("Entrega Prevista: ").append(dataHoraEntregaPrevista.format(FORMATADOR)).append("\n");
        }
        if (cartaoCredito != null && !cartaoCredito.isEmpty()) {
            // Apenas para fins de teste, mostra os últimos 4 dígitos
            String cartaoMascarado = "**** **** **** " + cartaoCredito.substring(Math.max(0, cartaoCredito.length() - 4));
            sb.append("Cartão: ").append(cartaoMascarado).append("\n");
        }
        sb.append("----------------------------\n");
        return sb.toString();
    }
}