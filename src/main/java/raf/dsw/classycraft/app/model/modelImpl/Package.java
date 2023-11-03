package raf.dsw.classycraft.app.model.modelImpl;

import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {

    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {

    }

    @Override
    public void removeChild(ClassyNode child) {

    }
}
