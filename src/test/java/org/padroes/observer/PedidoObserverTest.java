package org.padroes.observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PedidoObserverTest {

    @Test
    void deveNotificarUmCliente() {
        Pedido pedido = new Pedido("PED-1001");
        AppClienteObserver cliente = new AppClienteObserver("Joao");

        pedido.subscribe(cliente);
        pedido.atualizarStatus(Pedido.Status.PREPARANDO);

        assertEquals(
                "Cliente Joao: pedido PED-1001 agora esta PREPARANDO",
                cliente.getUltimaMensagem()
        );
    }

    @Test
    void deveNotificarTodosObserversDoPedido() {
        Pedido pedido = new Pedido("PED-1002");
        AppClienteObserver cliente = new AppClienteObserver("Ana");
        PainelCozinhaObserver cozinha = new PainelCozinhaObserver();
        AppEntregadorObserver entregador = new AppEntregadorObserver("Carlos");

        pedido.subscribe(cliente);
        pedido.subscribe(cozinha);
        pedido.subscribe(entregador);

        pedido.atualizarStatus(Pedido.Status.SAIU_PARA_ENTREGA);

        assertEquals(
                "Cliente Ana: pedido PED-1002 agora esta SAIU_PARA_ENTREGA",
                cliente.getUltimaMensagem()
        );
        assertEquals(
                "Cozinha: status atualizado para SAIU_PARA_ENTREGA",
                cozinha.getUltimoEvento()
        );
        assertEquals(
                "Entregador Carlos: retirar pedido PED-1002 para entrega",
                entregador.getUltimaAtualizacao()
        );
    }

    @Test
    void naoDeveNotificarClienteNaoInscrito() {
        Pedido pedido = new Pedido("PED-1003");
        AppClienteObserver cliente = new AppClienteObserver("Maria");

        pedido.atualizarStatus(Pedido.Status.ENTREGUE);

        assertNull(cliente.getUltimaMensagem());
    }

    @Test
    void deveNotificarClienteDoPedidoCorreto() {
        Pedido pedidoA = new Pedido("PED-A");
        Pedido pedidoB = new Pedido("PED-B");
        AppClienteObserver clienteA = new AppClienteObserver("Cliente A");
        AppClienteObserver clienteB = new AppClienteObserver("Cliente B");

        pedidoA.subscribe(clienteA);
        pedidoB.subscribe(clienteB);

        pedidoA.atualizarStatus(Pedido.Status.PREPARANDO);

        assertEquals(
                "Cliente Cliente A: pedido PED-A agora esta PREPARANDO",
                clienteA.getUltimaMensagem()
        );
        assertNull(clienteB.getUltimaMensagem());
    }

    @Test
    void naoDeveNotificarObserverAposUnsubscribe() {
        Pedido pedido = new Pedido("PED-1004");
        PainelCozinhaObserver cozinha = new PainelCozinhaObserver();

        pedido.subscribe(cozinha);
        pedido.atualizarStatus(Pedido.Status.PREPARANDO);
        pedido.unsubscribe(cozinha);
        pedido.atualizarStatus(Pedido.Status.ENTREGUE);

        assertEquals("Cozinha: iniciar preparo do pedido PED-1004", cozinha.getUltimoEvento());
    }
}

