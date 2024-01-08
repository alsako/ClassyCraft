package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteNodeAction extends AbstractClassyAction {

    public DeleteNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/minus.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete selected");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected==null)
            return;
        ClassyNode selectedClassyNode = selected.getClassyNode();
        if (selectedClassyNode instanceof DiagramElement) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.USE_SIDE_TOOLBAR_OPTION);
            return;
        } else if (selectedClassyNode instanceof Diagram) {
//            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
//            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
        }
        MainFrame.getInstance().getClassyTree().removeChild(selected);

    }
}
