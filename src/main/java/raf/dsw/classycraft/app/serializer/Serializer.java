package raf.dsw.classycraft.app.serializer;

import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Project;

import java.io.File;

public interface Serializer {

    void loadProject(File file);
    void saveProject(Project project);

    void loadDiagram(File file);
    public void loadDiagramContent(File file, Diagram toDiagram);
    void saveDiagram(Diagram diagram, String nameInput);
}
