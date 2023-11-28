package raf.dsw.classycraft.app.messagegen;

import raf.dsw.classycraft.app.observer.ISubscriber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImpl implements MessageGenerator {

    List<ISubscriber> subscribers;

    @Override
    public Message formulateMessage(Event event) {
        LocalDateTime timestamp = LocalDateTime.now();
        switch (event){
            case NAME_CANNOT_BE_EMPTY:
            case NODE_CANNOT_BE_DELETED:
            case CANNOT_ADD_CHILD_TO_LEAF:
                return new Message(MessageType.ERROR, timestamp, event.toString());
            case AUTHOR_MUST_BE_A_PROJECT:
            case NAME_TAKEN:
            case OPTION_NOT_SELECTED:
                return new Message(MessageType.INFO, timestamp, event.toString());
            case PARENT_NOT_SELECTED:
            case NODE_NOT_SELECTED:
                return new Message(MessageType.WARNING, timestamp, event.toString());
            //case PROBA:
               // return new Message(MessageType.INFO, timestamp, "OVO JE PROBA");

        }
        return null;
    }

    @Override
    public void generateMessage(Event event){
        Message message = formulateMessage(event);
        this.notifySubscribers(message);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object message) {
        if(message == null ||this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (ISubscriber sub: subscribers) {
            sub.update((Message)message);
        }
    }

}
