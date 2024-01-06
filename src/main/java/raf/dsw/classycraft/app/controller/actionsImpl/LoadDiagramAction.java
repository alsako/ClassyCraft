package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class LoadDiagramAction extends AbstractClassyAction {

    public LoadDiagramAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/template.png"));
        putValue(NAME, "Load template");
        putValue(SHORT_DESCRIPTION, "Load template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        ClassyNode selectedClassyNode = selected.getClassyNode();

        if (selectedClassyNode instanceof Package){
        JFileChooser jfc = new JFileChooser();
        String workingDirectory = System.getProperty("user.dir");
        String templatesPath = workingDirectory + "/src/main/resources/templates";
        jfc.setCurrentDirectory(new File(templatesPath));
        if(jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            try{
                File file = jfc.getSelectedFile();
                ApplicationFramework.getInstance().getSerializer().loadDiagram(file);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }else if (selectedClassyNode instanceof Diagram) {
            JFileChooser jfc = new JFileChooser();
            String workingDirectory = System.getProperty("user.dir");
            String templatesPath = workingDirectory + "/src/main/resources/templates";
            jfc.setCurrentDirectory(new File(templatesPath));
            if(jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
                try{
                    File file = jfc.getSelectedFile();
                    ApplicationFramework.getInstance().getSerializer().loadDiagramContent(file, (Diagram) selectedClassyNode);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
