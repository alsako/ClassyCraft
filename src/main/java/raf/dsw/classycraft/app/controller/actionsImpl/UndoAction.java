package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.NoninvertibleTransformException;

public class UndoAction extends AbstractClassyAction {

    public UndoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/undo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        try {
            PackageView trPackageView = MainFrame.getInstance().getPackageView();
            DiagramView trDiagramView = (DiagramView) trPackageView.getTabbedPane().getSelectedComponent();
            if (trDiagramView==null)
                return;
            trDiagramView.getCommandManager().undoCommand();
        }catch (NoninvertibleTransformException e1){
            e1.printStackTrace();
        }

    }
}
