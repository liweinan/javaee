package net.bluedash.ejb;

import javax.ejb.Local;

/**
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Local
public interface Counter {
    public void inc();

    public void reset();

    public int get();
}
