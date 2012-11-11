package net.bluedash.model.sandbox.collection.bag;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 11 11 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
@Table(name = "collection_bag_item")
public class Item {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    // @CollectionOfElements is now deprecated, instead use: @ElementCollection
    @ElementCollection
    @JoinTable(name = "collection_bag_link", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name="link_val", nullable = false)
    private Collection<String> links = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<String> getLinks() {
        return links;
    }

    public void setLinks(Collection<String> links) {
        this.links = links;
    }
}
