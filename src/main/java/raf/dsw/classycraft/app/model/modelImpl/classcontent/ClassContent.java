package raf.dsw.classycraft.app.model.modelImpl.classcontent;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class ClassContent implements IPublisher {

    private String name;
    private VisibilityTypes visibility;
    private String type;

    private transient List<ISubscriber> subscribers;

    public void setName(String name) {
        this.name = name;
        notifySubscribers(DiagramNtfType.REPAINT);
    }

    public void setVisibility(VisibilityTypes visibility) {
        this.visibility = visibility;
        notifySubscribers(DiagramNtfType.REPAINT);
    }

    public void setType(String type) {
        this.type = type;
        notifySubscribers(DiagramNtfType.REPAINT);
    }

    public ClassContent(String name, VisibilityTypes visibility, String type) {
        this.name = name;
        this.visibility = visibility;
        this.type = type;
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

    public void removeAllSubscribers(){
        if (this.subscribers==null)
            return;
        this.subscribers.clear();
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
