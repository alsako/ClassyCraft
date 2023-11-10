package raf.dsw.classycraft.app.model.modelImpl;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.DiagramNtfType;
import raf.dsw.classycraft.app.model.PackageNotification;
import raf.dsw.classycraft.app.model.PackageNtfType;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Package extends ClassyNodeComposite implements IPublisher {

    List<ISubscriber> subscribers;
    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void setName(String name) {
        if (name.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.NAME_CANNOT_BE_EMPTY);
            return;
        }
        if(((ClassyNodeComposite)(this.getParent())).childNameTaken(name)){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.NAME_TAKEN);
            return;
        }
        PackageNotification pn = new PackageNotification(name, PackageNtfType.RENAME);
        this.notifySubscribers(pn);
        super.setName(name);
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null) {
            if (child instanceof Diagram) {
                Diagram diagram = (Diagram) child;
                if (!this.getChildren().contains(diagram)) {
                    this.getChildren().add(diagram);
                }
                if(MainFrame.getInstance().getPackageView().getPack() != null
                        && MainFrame.getInstance().getPackageView().getPack().equals(this)) {
                    PackageNotification pn = new PackageNotification(child.getName(), PackageNtfType.ADD_CHILD);
                    this.notifySubscribers(pn);
                }
            } else if (child instanceof Package) {
                Package pack = (Package) child;
                if (!this.getChildren().contains(pack)) {
                    this.getChildren().add(pack);
                }
            }
        }
    }


    @Override
    public void removeChild(ClassyNode child) {
        if (child != null &&  child instanceof Diagram){
          if (child instanceof Diagram)
            ((Diagram)child).notifySubscribers(DiagramNtfType.DELETE);
        }
        else if(child!=null && child instanceof Package){
            ((Package)child).notifySubscribers(PackageNtfType.DELETE);
            for (ClassyNode child1:((Package) child).getChildren()) {
                if (child1 instanceof Diagram)
                    ((Diagram)child1).notifySubscribers(DiagramNtfType.DELETE);
            }
        }
        this.getChildren().remove(child);
    }

    public int countPackageChildren(){
        int i = 0;
        for (ClassyNode child:this.getChildren()) {
            if (child instanceof Package)
             i++;
        }
        return i;
    }

    public int countDiagramChildren(){
        int i = 0;
        for (ClassyNode child:this.getChildren()) {
            if (child instanceof Diagram)
                i++;
        }
        return i;
    }

    public List<String> getDiagramChildrenNames(){
        List<String> diagramChildren = new ArrayList<>();
        for (ClassyNode child:this.getChildren()) {
            if (child instanceof Diagram)
                diagramChildren.add(child.getName());
        }
        return diagramChildren;
    }

    public Project getParentProject(){
        if (this.getParent() instanceof Project)
            return (Project) this.getParent();
        else
            return ((Package)this.getParent()).getParentProject();
    }

    public Diagram getLastDiagramChild(){
        int length = this.getChildren().size();
        for (int i = length-1; i>=0; i--){
            if (this.getChildren().get(i) instanceof Diagram)
                return (Diagram) this.getChildren().get(i);
        }
        return null;
    }

    public String getAuthor(){
        return this.getParentProject().getAuthor();
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
    public void notifySubscribers(Object packageNotification) {
        if(packageNotification == null ||this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (ISubscriber sub: subscribers) {
            sub.update(packageNotification);
        }
    }
}
