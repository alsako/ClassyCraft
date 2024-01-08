package raf.dsw.classycraft.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.AgregacijaPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.GeneralizacijaPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.KompozicijaPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.ZavisnostPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.EnumPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterfejsPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.KlasaPainter;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Package;
import raf.dsw.classycraft.app.model.modelImpl.Project;
import raf.dsw.classycraft.app.model.modelImpl.classes.Enum;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.Klasa;
import raf.dsw.classycraft.app.model.modelImpl.connections.Agregacija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Generalizacija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Kompozicija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Zavisnost;
import java.io.*;
import java.util.List;

public class JacksonSerializer implements Serializer{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void loadProject(File file) {
        try(FileReader fileReader = new FileReader(file)){
            Project project = objectMapper.readValue(fileReader, Project.class);
            project.setParent(ApplicationFramework.getInstance().getClassyRepository().getRoot());
            ApplicationFramework.getInstance().getClassyRepository().getRoot().addChild(project);
            establishParentRelationships(project);
            MainFrame.getInstance().getClassyTree().loadProject(project);
            for (ClassyNode child: project.getChildren()) {
                if (child instanceof Package){
                    MainFrame.getInstance().getPackageView().updatePackageView((Package) child);
                    List<Diagram> diagramChildren = ((Package)child).getDiagramChildren();
                    for (Diagram diagram:diagramChildren) {
                        makePainters(diagram);
                    }
                }
            }
            return;
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void saveProject(Project project) {
        try(FileWriter fileWriter = new FileWriter(project.getFilePath())){
            objectMapper.writeValue(fileWriter, project);
            System.out.println("saved project " + project.getName());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void loadDiagram(File file) {
        //ako je otvoren package view nekog paketa dodaje u njega, ako nije dodaje u selektovani
        Package currentPackage = MainFrame.getInstance().getPackageView().getPack();
        if (currentPackage==null){
            ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
            ClassyNode selectedClassyNode = selected.getClassyNode();
            if (selectedClassyNode instanceof Package)
                currentPackage = (Package) selectedClassyNode;
        }
        try(FileReader fileReader = new FileReader(file)){
            Diagram diagram = objectMapper.readValue(fileReader, Diagram.class);
            diagram.setParent(currentPackage);
            currentPackage.addChild(diagram);
            establishParentRelationships(diagram);
            ((ClassyTreeImpl)MainFrame.getInstance().getClassyTree()).addToTree(currentPackage, diagram);
            MainFrame.getInstance().getPackageView().updatePackageView(currentPackage);
            makePainters(diagram);
            return;
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
    }
    @Override
    public void loadDiagramContent(File file, Diagram toDiagram){
        try(FileReader fileReader = new FileReader(file)){
            Diagram fromDiagram = objectMapper.readValue(fileReader, Diagram.class);
            toDiagram.setChildren(fromDiagram.getChildren());
            establishParentRelationships(toDiagram);
            Package currentPackage = (Package) toDiagram.getParent();
            MainFrame.getInstance().getPackageView().updatePackageView(currentPackage);
            makePainters(toDiagram);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveDiagram(Diagram diagram, String nameInput) {
        try{
            FileWriter fw = new FileWriter("src/main/resources/templates/" + nameInput + ".json", false);
            PrintWriter pw = new PrintWriter(fw);
            objectMapper.writeValue(pw, diagram);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void establishParentRelationships(ClassyNodeComposite parent) {
        List<ClassyNode>children = parent.getChildren();
        for (ClassyNode child : children) {
            child.setParent(parent);
            parent.addChild(child);
            if (child instanceof ClassyNodeComposite) {
                establishParentRelationships((ClassyNodeComposite) child);
            }
        }
    }

    private void makePainters(Diagram diagram){
        List<ClassyNode> diagramElements = diagram.getChildren();
        for(ClassyNode element : diagramElements){
            ElementPainter newPainter = null;
            if (element instanceof Klasa)
                newPainter = new KlasaPainter((Klasa) element);
            else if (element instanceof Interfejs)
                newPainter = new InterfejsPainter((Interfejs) element);
            else if (element instanceof Enum)
                newPainter = new EnumPainter((Enum) element);
            else if (element instanceof Agregacija) {
                newPainter = new AgregacijaPainter((Agregacija)element);
            } else if (element instanceof Kompozicija) {
                newPainter = new KompozicijaPainter((Kompozicija)element);
            } else if (element instanceof Zavisnost) {
                newPainter = new ZavisnostPainter((Zavisnost)element);
            }else if (element instanceof Generalizacija)
                newPainter = new GeneralizacijaPainter((Generalizacija)element);
            if (newPainter!=null)
                MainFrame.getInstance().getPackageView().addPainterForDiagram(diagram, newPainter);
        }
    }

}
