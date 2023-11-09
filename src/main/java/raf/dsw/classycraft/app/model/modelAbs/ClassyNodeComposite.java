package raf.dsw.classycraft.app.model.modelAbs;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class ClassyNodeComposite extends ClassyNode{

    List<ClassyNode> children;

    public ClassyNodeComposite(String name, ClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public ClassyNodeComposite() {
        this.children = new ArrayList<>();
    }

    public boolean childNameTaken(String name){
        for (ClassyNode child:this.getChildren()) {
            if (child.getName().trim().equals(name.trim()))
                return true;
        }
        return false;
    }

    public abstract void addChild(ClassyNode child);

    public abstract void removeChild(ClassyNode child);


}
