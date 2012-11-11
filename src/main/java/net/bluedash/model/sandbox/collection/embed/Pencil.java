package net.bluedash.model.sandbox.collection.embed;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 11 11 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
@Table(name = "collection_embed_pencil")
public class Pencil {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ElementCollection
    @JoinTable(name = "collection_embed_color", joinColumns = @JoinColumn(name = "pencil_id"))
    @Column(name = "color_val", nullable = false)
    // set do not support @CollectionId
    // http://lists.jboss.org/pipermail/hibernate-commits/2008-March/003270.html
    //    @CollectionId(columns = @Column(name = "color_id"), type = @Type(type = "string"), generator = "uuid")
    //    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Set<Color> colors = new HashSet<Color>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }
}
