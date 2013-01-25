package net.bluedash.controller.conversation;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 01 25 2013
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Named("fruitRepo")
@ConversationScoped
public class FruitRepository implements Serializable {

    private List<String> fruits = new ArrayList<String>();

    private String fruit;

    @Inject
    private Conversation conversation;

    boolean conversationStarted = false;

    public List<String> getFruits() {
        return fruits;
    }

    public void setFruits(List<String> fruits) {
        this.fruits = fruits;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public synchronized void add() {
        if(!conversationStarted) {
            conversation.begin();
            conversationStarted = true;
        }
        fruits.add(fruit);
    }

    public synchronized void clear() {
        conversation.end();
        conversationStarted = false;
        fruits = new ArrayList<String>();
    }

}
