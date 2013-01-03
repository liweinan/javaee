package net.bluedash.controller.playscope;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: weinanli
 * Date: 1/3/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@RequestScoped
public class AnotherCherryController implements Serializable {

    private String anotherCherry;

    public String getAnotherCherry() {
        return anotherCherry;
    }

    public void setAnotherCherry(String anotherCherry) {
        this.anotherCherry = anotherCherry;
    }
}
