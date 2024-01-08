package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.commands.DeleteCommand;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.ConnectionPainter;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.connections.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteElementAction extends AbstractClassyAction {
    public DeleteElementAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete Element");
        putValue(SHORT_DESCRIPTION, "Delete Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<ElementPainter> selectedList = MainFrame.getInstance().getPackageView().getCurrentDiagramView().getSelectedPainters();
        MainFrame.getInstance().getPackageView().startDeleteElementState();

        if (!(selectedList.isEmpty())) { //ako ima selektovanih desava se na klik
            DiagramView diagramView = MainFrame.getInstance().getPackageView().getCurrentDiagramView();
            DeleteCommand deleteCommand = new DeleteCommand(null, diagramView, diagramView.getSelectedPainters());
            diagramView.getCommandManager().addCommand(deleteCommand);
        }

    }
}
