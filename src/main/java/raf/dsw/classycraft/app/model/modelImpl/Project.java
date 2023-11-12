package raf.dsw.classycraft.app.model.modelImpl;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.DiagramNtfType;
import raf.dsw.classycraft.app.model.PackageNotification;
import raf.dsw.classycraft.app.model.PackageNtfType;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;

import java.nio.file.Path;
@Setter
@Getter
public class Project extends ClassyNodeComposite {

    public String author;
    public String folderPath;

    public Project(String name, ClassyNode parent) {
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
        super.setName(name);
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
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Package){
            Package pack = (Package) child;
            if (!this.getChildren().contains(pack)){
                this.getChildren().add(pack);
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
        }
    }
}
