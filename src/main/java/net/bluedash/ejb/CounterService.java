package net.bluedash.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Stateless
@Path("/counter")
public class CounterService {

    @EJB
    Counter counter;

    @GET
    @Path("/inc")
    public String inc() {
        counter.inc();
        return String.valueOf(counter.get());
    }

    @GET
    @Path("/")
    public String get() {
        return String.valueOf(counter.get());
    }

    @GET
    @Path("/reset")
    public String reset() {
        counter.reset();
        return "COUNTER-RESET";
    }
}
