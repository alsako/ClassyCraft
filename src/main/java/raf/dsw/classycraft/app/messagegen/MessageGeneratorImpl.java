package raf.dsw.classycraft.app.messagegen;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImpl implements MessageGenerator {

    List<ISubscriber> subscribers;

    @Override
    public Message generateMessage(Event event) {
        LocalDateTime timestamp = LocalDateTime.now();
        switch (event){
            case NAME_CANNOT_BE_EMPTY:
                return new Message(MessageType.ERROR, timestamp, event.toString());
            case NODE_CANNOT_BE_DELETED:
            case CANNOT_ADD_CHILD_TO_LEAF:
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
    public void notifySubscribers(Object event) {
        if(event == null ||this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (ISubscriber sub: subscribers) {
            sub.update(generateMessage((Event) event));
        }
    }

}
