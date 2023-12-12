package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
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



        if (selectedList.isEmpty()) {
            MainFrame.getInstance().getPackageView().startDeleteElementState();
        }
        else {
            List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getCurrentDiagramView().getPainters();
            List<ConnectionPainter> toRemove = new ArrayList<>();

            //koje su sve veze asocirane sa entitetima za uklanjanje
            for (ElementPainter selected:selectedList) {
                if (selected.getElement() instanceof Interclass) {
                    for (ElementPainter painter : diagramPainters) {
                        if (painter instanceof ConnectionPainter) {
                            if (((Connection)painter.getElement()).getAssociatedInterclasses().contains(selected.getElement())) {
                                if (!(selectedList).contains(painter))
                                    toRemove.add((ConnectionPainter) painter);
                            }
                        }
                    }
                }
            }
           //uklanjanje
            for (ConnectionPainter connectionPainter:toRemove) {
                MainFrame.getInstance().getPackageView().removePainterFromMap(connectionPainter);
            }
            for (ElementPainter selected:selectedList){
                MainFrame.getInstance().getPackageView().removePainterFromMap(selected);
            }

            MainFrame.getInstance().getPackageView().getCurrentDiagramView().deselectAll();

        }
    }
}
