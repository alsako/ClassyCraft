package raf.dsw.classycraft.app.model.factory;

import raf.dsw.classycraft.app.controller.actionsImpl.NewProjectAction;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.modelImpl.Package;
import raf.dsw.classycraft.app.model.modelImpl.Project;
import raf.dsw.classycraft.app.model.modelImpl.ProjectExplorer;

public class FactoryUtils {

    public static final PackageFactory packageFactory = new PackageFactory();
    public static final DiagramFactory diagramFactory = new DiagramFactory();
    public static final ProjectFactory projectFactory = new ProjectFactory();

    public static NodeFactory returnNodeFactory(ClassyNodeComposite parent){
        if(parent instanceof ProjectExplorer)
            return projectFactory;
        else if (parent instanceof Project) {
            return packageFactory;
        } else if (parent instanceof Package) {
            if (NewProjectAction.selectedOption.equalsIgnoreCase("package"))
                return packageFactory;
            else if (NewProjectAction.selectedOption.equalsIgnoreCase("diagram")) {
                return diagramFactory;
            }
            else return null;
        }
        else return null;
    }

}
