package com.boki.proxy.pureproxy.concreteproxy.code;

public class ConcreteClient {

    // ConcreteLogic, TimeProxy 모두 주입 가능
    private final ConcreteLogic concreteLogic;

    public ConcreteClient(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    public void execute() {
        concreteLogic.operation();
    }

}
