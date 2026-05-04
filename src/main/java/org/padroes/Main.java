package org.padroes;

import org.padroes.observer.AppClienteObserver;
import org.padroes.observer.AppEntregadorObserver;
import org.padroes.observer.PainelCozinhaObserver;
import org.padroes.observer.Pedido;

public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido("PED-1001");

        AppClienteObserver cliente = new AppClienteObserver("Joao");
        PainelCozinhaObserver cozinha = new PainelCozinhaObserver();
        AppEntregadorObserver entregador = new AppEntregadorObserver("Marcos");

        pedido.subscribe(cliente);
        pedido.subscribe(cozinha);
        pedido.subscribe(entregador);

        System.out.println("=== Fluxo do pedido " + pedido.getCodigo() + " ===");

        pedido.atualizarStatus(Pedido.Status.CRIADO);
        pedido.atualizarStatus(Pedido.Status.PREPARANDO);

        System.out.println("\n--- Cozinha nao precisa mais receber eventos deste pedido ---");
        pedido.unsubscribe(cozinha);

        pedido.atualizarStatus(Pedido.Status.SAIU_PARA_ENTREGA);
        pedido.atualizarStatus(Pedido.Status.ENTREGUE);
    }
}