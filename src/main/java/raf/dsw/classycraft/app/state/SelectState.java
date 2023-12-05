package raf.dsw.classycraft.app.state;


import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.HighlightPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.SelectionPainter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.List;

public class SelectState implements ClassyState {

    SelectionPainter painter = null;
    Point initialPoint = null;
    @Override
    public void misKliknut(Point p, DiagramView diagramView) {
        MainFrame.getInstance().getPackageView().deselectAll();
        painter = new SelectionPainter(p.x, p.y);
        initialPoint = new Point(p.x, p.y);
        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());

        for (int i=diagramPainters.size()-1; i>=0; i--){
            if (diagramPainters.get(i).elementAt(p.x, p.y)){
                MainFrame.getInstance().getPackageView().getSelectedPainters().add(diagramPainters.get(i));
            }
        }
        MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram()).add(painter);
    }

    @Override
    public void misPrevucen(Point p, DiagramView diagramView) {
        painter.calculateCoordinates(initialPoint.x, initialPoint.y, p.x, p.y);
        diagramView.repaint();
    }

    @Override
    public void misOtpusten(Point p, DiagramView diagramView) {
        painter.calculateCoordinates(initialPoint.x, initialPoint.y, p.x, p.y);
        MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram()).remove(painter);

        if (painter.getShape()==null)
            return;

        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());
        for (ElementPainter element : diagramPainters) {
            if (element.getShape().intersects((Rectangle2D) painter.getShape())){
                if (!(MainFrame.getInstance().getPackageView().getSelectedPainters().contains(element)))
                MainFrame.getInstance().getPackageView().getSelectedPainters().add(element);
            }
        }
        List<ElementPainter> selected = MainFrame.getInstance().getPackageView().getSelectedPainters();
        System.out.println(selected);

        for (ElementPainter element:selected) {
            HighlightPainter highlightPainter = new HighlightPainter(element);
            diagramView.getHighlights().add(highlightPainter);
        }
        diagramView.repaint();
        painter=null;
    }
}
