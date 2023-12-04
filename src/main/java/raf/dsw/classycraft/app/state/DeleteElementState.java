package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.controller.actionsImpl.NewConnectionAction;
import raf.dsw.classycraft.app.gui.swing.painters.*;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.connections.*;

import java.awt.*;
import java.text.spi.BreakIteratorProvider;
import java.util.List;

public class DeleteElementState implements ClassyState{
    @Override
    public void misKliknut(Point p, DiagramView diagramView) {

        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());

        DiagramElement removed = null;

        for (ElementPainter painter:diagramPainters) {
            if (painter.elementAt(p.x, p.y)){
                MainFrame.getInstance().getPackageView().removePainterFromMap(painter);
                removed = painter.getElement();
                ((ClassyTreeImpl)MainFrame.getInstance().getClassyTree()).removeFromTree(painter.getElement());
                break;
            }
        }
        if (removed instanceof Interclass) {
            for (ElementPainter painter : diagramPainters) {
                if (painter instanceof ConnectionPainter) {
                    if (((Connection)painter.getElement()).getAssociatedInterclasses().contains(removed)) {
                        MainFrame.getInstance().getPackageView().removePainterFromMap(painter);
                        ((ClassyTreeImpl) MainFrame.getInstance().getClassyTree()).removeFromTree(painter.getElement());
                        return;
                    }

                }
            }
        }

    }

    @Override
    public void misPrevucen(Point p, DiagramView diagramView) {
        //ne treba
    }

    @Override
    public void misOtpusten(Point p, DiagramView diagramView) {
        //ne treba
    }
}
