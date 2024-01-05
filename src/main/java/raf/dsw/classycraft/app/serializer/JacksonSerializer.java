package raf.dsw.classycraft.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Project;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JacksonSerializer implements Serializer{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Project loadProject(File file) {

        try(FileReader fileReader = new FileReader(file)){
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(Project project) {
        try(FileWriter fileWriter = new FileWriter(project.getFolderPath())){
            objectMapper.writeValue(fileWriter, project);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveDiagram(Diagram diagram) {

    }
}
