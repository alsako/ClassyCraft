package raf.dsw.classycraft.app.model.modelImpl;

import lombok.Getter;
import lombok.Setter;
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
            this.getChildren().remove(child);
        }
    }
}
