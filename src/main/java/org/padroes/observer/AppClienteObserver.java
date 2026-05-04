package org.padroes.observer;

public class AppClienteObserver implements PedidoObserver {

    private final String nomeCliente;
    private String ultimaMensagem;

    public AppClienteObserver(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getUltimaMensagem() {
        return ultimaMensagem;
    }

    @Override
    public void update(Pedido pedido) {
        this.ultimaMensagem = "Cliente " + nomeCliente
                + ": pedido " + pedido.getCodigo()
                + " agora esta " + pedido.getStatus();
        System.out.println(ultimaMensagem);
    }
}

