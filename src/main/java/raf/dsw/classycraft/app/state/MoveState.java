package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.connections.Connection;

import java.awt.*;
import java.util.List;

public class MoveState implements ClassyState{

    ElementPainter current = null;
    @Override
    public void misKliknut(Point p, DiagramView diagramView) {
        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());
        for (int i=diagramPainters.size()-1; i>=0; i--){
            if (diagramPainters.get(i) instanceof InterclassPainter && diagramPainters.get(i).elementAt(p.x, p.y)) {
                current = diagramPainters.get(i);
                return;
            }
        }

    }

    @Override
    public void misPrevucen(Point p, DiagramView diagramView) {
        if (current==null)
            return;
        ((Interclass)current.getElement()).setBasedOnCenterpoint(p);
        diagramView.repaint();
    }

    @Override
    public void misOtpusten(Point p, DiagramView diagramView) {
        if (current==null)
            return;
        ((Interclass)current.getElement()).setBasedOnCenterpoint(p);
        diagramView.repaint();
        current = null;
    }
}
