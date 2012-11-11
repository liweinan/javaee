package net.bluedash.model.sandbox.onetoone;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 11 11 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
@Table(name = "onetoone_address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(generator = "foreign")
    @GenericGenerator(name = "foreign", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "user"))
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
