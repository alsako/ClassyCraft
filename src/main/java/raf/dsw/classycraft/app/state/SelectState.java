package raf.dsw.classycraft.app.state;


import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.HighlightPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.SelectionPainter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class SelectState implements ClassyState {

    SelectionPainter painter = null;
    Point initialPoint = null;
    @Override
    public void misKliknut(Point p, DiagramView diagramView) {
        diagramView.deselectAll();
        painter = new SelectionPainter(p.x, p.y);
        initialPoint = new Point(p.x, p.y);
        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());

        for (int i=diagramPainters.size()-1; i>=0; i--){
            if (diagramPainters.get(i).elementAt(p.x, p.y)){
                diagramView.getSelectedPainters().add(diagramPainters.get(i));
            }
        }
        MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram()).add(painter);
        painter.addSubscriber(diagramView);

    }

    @Override
    public void misPrevucen(Point p, DiagramView diagramView) {
        painter.calculateCoordinates(initialPoint.x, initialPoint.y, p.x, p.y);
    }

    @Override
    public void misOtpusten(Point p, DiagramView diagramView) {
        if (initialPoint!=null && p!=null)
            painter.calculateCoordinates(initialPoint.x, initialPoint.y, p.x, p.y);
        MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram()).remove(painter);
        painter.removeSubscriber(diagramView);

        if (painter.getShape()==null)
            return;

        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());
        for (ElementPainter element : diagramPainters) {
            if (element.getShape().intersects((Rectangle2D) painter.getShape())){
                if (!(diagramView.getSelectedPainters().contains(element)))
                diagramView.getSelectedPainters().add(element);
            }
        }
        List<ElementPainter> selected = diagramView.getSelectedPainters();

        for (ElementPainter element:selected) { //prave se painteri za isticanje za sve selektovane
            HighlightPainter highlightPainter = new HighlightPainter(element);
            diagramView.getHighlights().add(highlightPainter);
        }
        diagramView.repaint();
        painter=null;
    }
}
