package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import java.awt.*;
import java.util.List;

public class MoveState implements ClassyState{

    ElementPainter current = null;
    Point initialPoint = null;
    @Override
    public void misKliknut(Point p, DiagramView diagramView) {
        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());
        List<ElementPainter> selectedList = MainFrame.getInstance().getPackageView().getSelectedPainters();

        if (selectedList.isEmpty())
            for (int i=diagramPainters.size()-1; i>=0; i--){
                if (diagramPainters.get(i) instanceof InterclassPainter && diagramPainters.get(i).elementAt(p.x, p.y)) {
                    current = diagramPainters.get(i);
                    return;
                }
            }
        initialPoint = p;
    }

    @Override
    public void misPrevucen(Point p, DiagramView diagramView) {
        List<ElementPainter> selectedList = MainFrame.getInstance().getPackageView().getSelectedPainters();

        if (selectedList.isEmpty()){
            if (current==null)
                return;
            ((Interclass)current.getElement()).setBasedOnCenterpoint(p);
             diagramView.repaint();
        } else {
            for (ElementPainter painter:selectedList) {
                if (painter instanceof InterclassPainter){
                    ((Interclass)painter.getElement()).translate(translationVector(initialPoint, p));
                }
            }
            initialPoint = p;
            diagramView.repaint();
        }

    }

    @Override
    public void misOtpusten(Point p, DiagramView diagramView) {
        List<ElementPainter> selectedList = MainFrame.getInstance().getPackageView().getSelectedPainters();

        if (selectedList.isEmpty()) {
            if (current == null)
                return;
            ((Interclass) current.getElement()).setBasedOnCenterpoint(p);
            diagramView.repaint();
            current = null;
        }else {
            for (ElementPainter painter:selectedList) {
                if (painter instanceof InterclassPainter){
                    ((Interclass)painter.getElement()).translate(translationVector(initialPoint, p));
                }
            }
            diagramView.repaint();
        }
    }

    private Point translationVector(Point a, Point b){
        double initialX = a.x;
        double initialY = a.y;
        double finalX = b.x;
        double finalY = b.y;
        int deltaX = Math.abs(a.x-b.x);
        int deltaY = Math.abs(a.y-b.y);

        if (finalX>=initialX && finalY>=initialY){
            return new Point(deltaX, deltaY);
        } else if (finalX>=initialX && finalY<=initialY) {
            return new Point(deltaX, -deltaY);
        } else if (finalX<=initialX && finalY<=initialY) {
            return new Point(-deltaX, -deltaY);
        }else{
            return new Point(-deltaX, deltaY);
        }
    }

}
