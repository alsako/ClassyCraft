package raf.dsw.classycraft.app.model.factory;

import lombok.NoArgsConstructor;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Package;

@NoArgsConstructor
public class DiagramFactory extends NodeFactory{
    @Override
    public ClassyNode createNode(ClassyNodeComposite parent) {

        int num = ((Package) parent).countDiagramChildren() + 1;
        return new Diagram("Diagram " + num, parent);
    }
}
