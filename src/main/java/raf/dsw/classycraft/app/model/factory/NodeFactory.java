package raf.dsw.classycraft.app.model.factory;

import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;

public abstract class NodeFactory {

    public ClassyNode orderNode(ClassyNodeComposite parent){
        ClassyNode node = createNode(parent);
        return node;
    }

    public abstract ClassyNode createNode(ClassyNodeComposite parent);
}
