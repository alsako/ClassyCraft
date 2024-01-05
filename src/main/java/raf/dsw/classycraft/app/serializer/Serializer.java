package raf.dsw.classycraft.app.serializer;

import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Project;

import java.io.File;

public interface Serializer {

    Project loadProject(File file);
    void saveProject(Project project);
    void saveDiagram(Diagram diagram);
}
