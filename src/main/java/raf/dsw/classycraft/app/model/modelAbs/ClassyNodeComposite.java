package raf.dsw.classycraft.app.model.modelAbs;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode{

    List<ClassyNode> children;

    public ClassyNodeComposite(String name, ClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public abstract void addChild(ClassyNode child);

    public abstract void removeChild(ClassyNode child);

}
