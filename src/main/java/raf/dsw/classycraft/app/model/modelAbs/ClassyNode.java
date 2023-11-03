package raf.dsw.classycraft.app.model.modelAbs;

public abstract class ClassyNode {

    String name;
    ClassyNode parent;

    public ClassyNode(String name, ClassyNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

}
