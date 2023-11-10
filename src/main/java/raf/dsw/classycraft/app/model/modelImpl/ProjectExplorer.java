package raf.dsw.classycraft.app.model.modelImpl;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.PackageNtfType;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {

    public ProjectExplorer(String name) {
        super(name, null);
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

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Project){
            Project project = (Project) child;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }


    }

    @Override
    public void removeChild(ClassyNode child) {
        if (child != null &&  child instanceof Project){
            for (ClassyNode child1:((Project)child).getChildren()) {
                ((Package)child1).notifySubscribers(PackageNtfType.DELETE);
            }
            this.getChildren().remove(child);
        }
    }
}
