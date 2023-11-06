package raf.dsw.classycraft.app.model.factory;

import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.modelImpl.Project;

public class ProjectFactory extends NodeFactory{
    @Override
    public ClassyNode createNode(ClassyNodeComposite parent) {

        int num = parent.getChildren().size() + 1;
        return new Project("Project " + num, parent);
    }
}
