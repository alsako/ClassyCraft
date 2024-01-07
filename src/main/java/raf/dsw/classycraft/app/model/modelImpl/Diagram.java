package raf.dsw.classycraft.app.model.modelImpl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class Diagram extends ClassyNodeComposite implements IPublisher {
    @JsonIgnore
    transient List<ISubscriber> subscribers;
    @JsonIgnore
    public String filePath;



    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
    }


    public String getFilePath() {
        return filePath;
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof DiagramElement){
            DiagramElement element = (DiagramElement) child;
            if (!this.getChildren().contains(element)){
                this.getChildren().add(element);
                changed();
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        if (child != null &&  child instanceof DiagramElement){
            this.getChildren().remove(child);
            changed();
        }
    }

    @Override
    public void setName(String name) {
        if (name.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.NAME_CANNOT_BE_EMPTY);
            return;
        }
        if (this.getParent()!=null)
            if(((ClassyNodeComposite)(this.getParent())).childNameTaken(name)){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.NAME_TAKEN);
                return;
            }
        super.setName(name);
        this.notifySubscribers(DiagramNtfType.RENAME);
        if (this.getParent()!=null)
            changed();
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

    public void changed(){
        Package parent = (Package) this.getParent();
        parent.getParentProject().setChanged(true);
    }
}
