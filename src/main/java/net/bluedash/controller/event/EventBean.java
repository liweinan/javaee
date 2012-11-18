package net.bluedash.controller.event;

import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * 11 17 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Named
@RequestScoped
public class EventBean implements Serializable {
    @Inject
    @Category("EVENT-BEAN")
    private Logger log;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void valueChanged(ValueChangeEvent event) {
        log.info(event.getNewValue().toString());
        value = event.getNewValue().toString();
    }
}
