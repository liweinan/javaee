package net.bluedash.model.sandbox.collection.orphan;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 11 15 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
public class KeyRing implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name="ring_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "ring", fetch = FetchType.LAZY)
    // The following is deprecated, 'orphanRemoval' is used instead
    // @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public Set<Key> keys = new HashSet<Key>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Key> getKeys() {
        return keys;
    }

    public void setKeys(Set<Key> keys) {
        this.keys = keys;
    }

    public void addKey(Key key) {
        keys.add(key);
        key.setRing(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
