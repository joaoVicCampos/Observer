package org.padroes.observer;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements PedidoSubject {

    public enum Status {
        CRIADO,
        PREPARANDO,
        SAIU_PARA_ENTREGA,
        ENTREGUE
    }

    private final String codigo;
    private final List<PedidoObserver> observers;
    private Status status;

    public Pedido(String codigo) {
        this.codigo = codigo;
        this.status = Status.CRIADO;
        this.observers = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public Status getStatus() {
        return status;
    }

    public void atualizarStatus(Status novoStatus) {
        this.status = novoStatus;
        notifyObservers();
    }

    @Override
    public void subscribe(PedidoObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(PedidoObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PedidoObserver observer : observers) {
            observer.update(this);
        }
    }
}

