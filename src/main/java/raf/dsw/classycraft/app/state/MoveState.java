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

    Boolean somethingSelected = false;
    @Override
    public void misKliknut(Point p, DiagramView diagramView) {
        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());
        List<ElementPainter> selectedList = MainFrame.getInstance().getPackageView().getSelectedPainters();

        if (selectedList.isEmpty()) //nema selecta
            for (int i=diagramPainters.size()-1; i>=0; i--){
                if (diagramPainters.get(i) instanceof InterclassPainter && diagramPainters.get(i).elementAt(p.x, p.y)) {
                    current = diagramPainters.get(i);
                    somethingSelected = true;
                    return;
                }
            }
        else
            somethingSelected = true;
        initialPoint = p;
    }

    @Override
    public void misPrevucen(Point p, DiagramView diagramView) {
        List<ElementPainter> selectedList = MainFrame.getInstance().getPackageView().getSelectedPainters();


        if (!selectedList.isEmpty()){ //pomeraju se selektovani elementi
            for (ElementPainter painter:selectedList) {
                if (painter instanceof InterclassPainter){
                    ((Interclass)painter.getElement()).translate(translationVector(initialPoint, p));
                }
            }
            initialPoint = p;
            diagramView.repaint();
        } else if (somethingSelected){ //pomera se jedan element
            if (current==null)
                return;
            ((Interclass)current.getElement()).setBasedOnCenterpoint(p);
//            diagramView.repaint();
        } else { //pomera se sam panel
            diagramView.moveView(translationVector(initialPoint, p).x, translationVector(initialPoint, p).getY());
            initialPoint = p;
//            diagramView.repaint();
        }

    }

    @Override
    public void misOtpusten(Point p, DiagramView diagramView) {
        List<ElementPainter> selectedList = MainFrame.getInstance().getPackageView().getSelectedPainters();

        if (!selectedList.isEmpty()) { //lista selektovanih
            for (ElementPainter painter:selectedList) {
                if (painter instanceof InterclassPainter){
                    ((Interclass)painter.getElement()).translate(translationVector(initialPoint, p));
                }
            }
            somethingSelected = false;
//            diagramView.repaint();
        }else if (somethingSelected){ //pojedinacni element
            if (current == null)
                return;
            ((Interclass) current.getElement()).setBasedOnCenterpoint(p);
            diagramView.repaint();
            current = null;
            somethingSelected = false;
        } else { //ceo panel
            diagramView.moveView(translationVector(initialPoint, p).x, translationVector(initialPoint, p).getY());
            initialPoint = p;
            somethingSelected = false;
//            diagramView.repaint();
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
