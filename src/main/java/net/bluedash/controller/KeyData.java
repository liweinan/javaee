package net.bluedash.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 11 15 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Named
@SessionScoped
public class KeyData implements Serializable {

    private static List keys = new ArrayList();

    public List getKeys() {
        return keys;
    }

    public static void setKeys(List keys) {
        KeyData.keys = keys;
    }
}
