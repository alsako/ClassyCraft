package raf.dsw.classycraft.app.model.modelImpl;

import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;

import java.nio.file.Path;

public class Project extends ClassyNodeComposite {

    public String author;
    public String folderPath;

    public Project(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {

    }

    @Override
    public void removeChild(ClassyNode child) {

    }
}
