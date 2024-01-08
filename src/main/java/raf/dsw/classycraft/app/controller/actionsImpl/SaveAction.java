package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAction extends AbstractClassyAction {

    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser jfc = new JFileChooser();
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        ClassyNode selectedClassyNode = selected.getClassyNode();

        if (selectedClassyNode instanceof Project) {
            Project project = (Project) selectedClassyNode;
            File projectFile = null;

            if (!project.changed) {
                return;
            }

            if (project.getFilePath() == null || project.getFilePath().isEmpty()) {
                if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc.getSelectedFile();
                    project.setFilePath(projectFile.getPath());
                } else {
                    return;
                }

            }
            ApplicationFramework.getInstance().getSerializer().saveProject(project);
            project.setChanged(false);
        } else if (selectedClassyNode instanceof Diagram){
            Diagram diagram = (Diagram) selectedClassyNode;
            String nameInput = JOptionPane.showInputDialog(new JFrame(), "Enter template name", "Name entry", JOptionPane.PLAIN_MESSAGE);
            if (nameInput == null || nameInput.trim().isEmpty()) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.NAME_CANNOT_BE_EMPTY);
                return;
            }
            ApplicationFramework.getInstance().getSerializer().saveDiagram(diagram, nameInput);
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.SAVE_PROJECT_OR_DIAGRAM);
        }
    }
}
