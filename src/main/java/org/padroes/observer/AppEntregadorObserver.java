package org.padroes.observer;

public class AppEntregadorObserver implements PedidoObserver {

    private final String nomeEntregador;
    private String ultimaAtualizacao;

    public AppEntregadorObserver(String nomeEntregador) {
        this.nomeEntregador = nomeEntregador;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    @Override
    public void update(Pedido pedido) {
        if (pedido.getStatus() == Pedido.Status.SAIU_PARA_ENTREGA) {
            this.ultimaAtualizacao = "Entregador " + nomeEntregador
                    + ": retirar pedido " + pedido.getCodigo() + " para entrega";
        } else if (pedido.getStatus() == Pedido.Status.ENTREGUE) {
            this.ultimaAtualizacao = "Entregador " + nomeEntregador
                    + ": pedido " + pedido.getCodigo() + " entregue";
        } else {
            this.ultimaAtualizacao = "Entregador " + nomeEntregador
                    + ": aguardando despacho do pedido " + pedido.getCodigo();
        }

        System.out.println(ultimaAtualizacao);
    }
}

