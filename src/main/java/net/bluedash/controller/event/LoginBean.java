package net.bluedash.controller.event;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 11 18 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Named
@RequestScoped
public class LoginBean {
    private String name;
    private String password;

    @Inject
    @Category("LOGIN-BEAN")
    Logger logger;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String verify() {
        logger.info("NAME: " + name);
        logger.info("PASSWORD: " + password);
        if (name.equals("admin") && password.equals("admin"))
            return "success";
        return "fail";
    }
}
