package raf.dsw.classycraft.app.model.modelImpl;

import lombok.Setter;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;

@Setter
public class Package extends ClassyNodeComposite {



    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Diagram){
            Diagram diagram = (Diagram) child;
            if (!this.getChildren().contains(diagram)){
                this.getChildren().add(diagram);
            }
        }
    }


    @Override
    public void removeChild(ClassyNode child) {

    }
}
