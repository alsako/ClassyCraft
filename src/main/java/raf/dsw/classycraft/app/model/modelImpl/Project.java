package raf.dsw.classycraft.app.model.modelImpl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;
import raf.dsw.classycraft.app.model.notifications.PackageNotification;
import raf.dsw.classycraft.app.model.notifications.PackageNtfType;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Project extends ClassyNodeComposite {

    public String author;
    public String filePath;
    @JsonIgnore
    public Boolean changed = false;
    public Project(String name, ClassyNode parent) {
        super(name, parent);
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
        changed = true;
    }

    public void setAuthor(String author) {
        if (author==null)
            return;
        if (author.isEmpty())
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.NAME_CANNOT_BE_EMPTY);
        for (ClassyNode child:this.getChildren()) {
            PackageNotification pn = new PackageNotification(author, PackageNtfType.AUTHOR_CHANGED);
            ((Package)child).notifySubscribers(pn);
            if (!((Package)child).getChildren().isEmpty()){
                for (ClassyNode subchild : ((Package)child).getChildren()) {
                    if (subchild instanceof Package)
                        ((Package)subchild).notifySubscribers(pn);
                }
            }
        }
        this.author = author;
        changed = true;
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Package){
            Package pack = (Package) child;
            if (!this.getChildren().contains(pack)){
                this.getChildren().add(pack);
                changed = true;
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        if (child != null &&  child instanceof Package){
            ((Package)child).notifySubscribers(PackageNtfType.DELETE);
            for (ClassyNode child1:((Package) child).getChildren()) {
                if (child1 instanceof Diagram)
                    ((Diagram)child1).notifySubscribers(DiagramNtfType.DELETE);
            }
            this.getChildren().remove(child);
            changed = true;
        }
    }

}
