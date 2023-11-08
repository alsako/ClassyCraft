package raf.dsw.classycraft.app.model.modelImpl;

import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class Diagram extends ClassyNode implements IPublisher {

    List<ISubscriber> subscribers;

    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
    }

    public Diagram() {
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
    public void notifySubscribers(Object diagramNotification) {
        if(diagramNotification == null ||this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (ISubscriber sub: subscribers) {
            sub.update(diagramNotification);
        }
    }
}
