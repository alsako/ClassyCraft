package raf.dsw.classycraft.app.model.modelImpl.classcontent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelImpl.classes.*;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Metoda.class, name = "Metoda"),
        @JsonSubTypes.Type(value = Atribut.class, name = "Atribut")
})
public abstract class ClassContent implements IPublisher {

    private String name;
    private VisibilityTypes visibility;
    private String contentType;
    @JsonIgnore
    private transient List<ISubscriber> subscribers;

    public ClassContent(String name, VisibilityTypes visibility, String contentType) {
        this.name = name;
        this.visibility = visibility;
        this.contentType = contentType;
    }


    public void setName(String name) {
        this.name = name;
        notifySubscribers(DiagramNtfType.REPAINT);
    }

    public void setVisibility(VisibilityTypes visibility) {
        this.visibility = visibility;
        notifySubscribers(DiagramNtfType.REPAINT);
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
        notifySubscribers(DiagramNtfType.REPAINT);
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
