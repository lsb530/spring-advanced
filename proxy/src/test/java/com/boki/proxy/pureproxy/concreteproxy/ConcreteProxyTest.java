package com.boki.proxy.pureproxy.concreteproxy;

import com.boki.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import com.boki.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient concreteClient = new ConcreteClient(concreteLogic);
        concreteClient.execute();
    }

}
