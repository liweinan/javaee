package net.bluedash.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.io.Serializable;

/**
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Stateful
public class CounterImpl implements Counter, Serializable {

    private int val;

    public void inc() {
        val++;
    }

    @PostConstruct
    public void init() {
        System.out.println("COUNTER-INIT");
        val = 0;
    }

    @Remove
    public void remove() {
        System.out.println("COUNTER-REMOVE");
    }

    public synchronized void reset() {
        System.out.println("COUNTER-RESET");
        val = 0;
    }

    public int get() {
        return val;
    }
}
