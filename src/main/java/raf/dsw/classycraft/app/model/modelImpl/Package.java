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
        if (child != null) {
            if (child instanceof Diagram) {
                Diagram diagram = (Diagram) child;
                if (!this.getChildren().contains(diagram)) {
                    this.getChildren().add(diagram);
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
          this.getChildren().remove(child);
        }
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

}
