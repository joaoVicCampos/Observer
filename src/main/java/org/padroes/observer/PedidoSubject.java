package org.padroes.observer;

public interface PedidoSubject {
    void subscribe(PedidoObserver observer);

    void unsubscribe(PedidoObserver observer);

    void notifyObservers();
}

