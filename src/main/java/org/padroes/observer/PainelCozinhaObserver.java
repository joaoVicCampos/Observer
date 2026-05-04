package org.padroes.observer;

public class PainelCozinhaObserver implements PedidoObserver {

    private String ultimoEvento;

    public String getUltimoEvento() {
        return ultimoEvento;
    }

    @Override
    public void update(Pedido pedido) {
        if (pedido.getStatus() == Pedido.Status.PREPARANDO) {
            this.ultimoEvento = "Cozinha: iniciar preparo do pedido " + pedido.getCodigo();
        } else if (pedido.getStatus() == Pedido.Status.ENTREGUE) {
            this.ultimoEvento = "Cozinha: pedido " + pedido.getCodigo() + " finalizado";
        } else {
            this.ultimoEvento = "Cozinha: status atualizado para " + pedido.getStatus();
        }

        System.out.println(ultimoEvento);
    }
}

