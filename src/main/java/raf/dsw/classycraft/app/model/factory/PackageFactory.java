package raf.dsw.classycraft.app.model.factory;

import lombok.NoArgsConstructor;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.modelImpl.Package;
import raf.dsw.classycraft.app.model.modelImpl.Project;

import java.util.Random;

@NoArgsConstructor
public class PackageFactory extends NodeFactory{



    @Override
    public ClassyNode createNode(ClassyNodeComposite parent) {

        int num = parent.getChildren().size() + 1;
        return new Package("Package " + num, parent);
    }
}
