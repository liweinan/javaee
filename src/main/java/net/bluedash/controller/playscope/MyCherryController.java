package net.bluedash.controller.playscope;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
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
public class MyCherryController implements Serializable {

    private String myCherry;

    @Inject
    private AnotherCherryController anotherCherryController;

    public void setAnotherCherryController(AnotherCherryController anotherCherryController) {
        this.anotherCherryController = anotherCherryController;
    }

    public AnotherCherryController getAnotherCherryController() {
        return anotherCherryController;
    }

    public String getAnotherCherry() {
        if (anotherCherryController != null)
            return anotherCherryController.getAnotherCherry();
        else return null;
    }

    public String getMyCherry() {
        return myCherry;
    }

    public void setMyCherry(String myCherry) {
        this.myCherry = myCherry;
    }

    public void whereIsMyCherry() {
        System.out.println("MY-CHERRY: " + myCherry);
        System.out.println("ANOTHER-CHERRY: " + anotherCherryController.getAnotherCherry());
    }
}
