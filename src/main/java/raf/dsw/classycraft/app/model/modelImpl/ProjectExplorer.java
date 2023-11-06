package raf.dsw.classycraft.app.model.modelImpl;

import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {

    public ProjectExplorer(String name) {
        super(name, null);
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
            this.getChildren().remove(child);
        }
    }
}
