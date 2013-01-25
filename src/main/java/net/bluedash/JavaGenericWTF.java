package net.bluedash;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 01 17 2013
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
public class JavaGenericWTF {

    public static void main(String[] args) {
        List arrs = new ArrayList<String>();
        arrs.add(1);
        arrs.add("B");
        for (Object o : arrs) {
            System.out.println(o.getClass());
        }

        List<String> arrs2 = arrs;
        Iterator iter = arrs2.iterator();
        while(iter.hasNext()) {
            Object obj = iter.next();
            System.out.println(obj);
        }


    }
}
