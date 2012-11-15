package net.bluedash.model.sandbox.collection.orphan;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 11 15 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
public class Key implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(optional = false)
    KeyRing ring;

    @Column(nullable = false)
    private String color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KeyRing getRing() {
        return ring;
    }

    public void setRing(KeyRing ring) {
        this.ring = ring;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
