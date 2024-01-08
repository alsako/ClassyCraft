package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.commands.MoveCommand;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.Utility;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import java.awt.*;
import java.util.List;

public class MoveState implements ClassyState{

    ElementPainter current = null;
    Point currentPoint = null;
    Point initialPoint = null;

    Boolean somethingSelected = false;
    @Override
    public void misKliknut(Point p, DiagramView diagramView) {
        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());
        List<ElementPainter> selectedList = diagramView.getSelectedPainters();



        if (selectedList.isEmpty()) //nema selecta
            for (int i=diagramPainters.size()-1; i>=0; i--){
                if (diagramPainters.get(i) instanceof InterclassPainter && diagramPainters.get(i).elementAt(p.x, p.y)) {
                    current = diagramPainters.get(i);
                    somethingSelected = true;
                    initialPoint = new Point((int)((Interclass)current.getElement()).getX(),(int)((Interclass)current.getElement()).getY());
                    return;
                }
            }
        else {
            somethingSelected = true;
            initialPoint = p;
        }
        currentPoint = p;
    }

    @Override
    public void misPrevucen(Point p, DiagramView diagramView) {
        List<ElementPainter> selectedList = diagramView.getSelectedPainters();


        if (!selectedList.isEmpty()){ //pomeraju se selektovani elementi
            for (ElementPainter painter:selectedList) {
                if (painter instanceof InterclassPainter){
                    ((Interclass)painter.getElement()).translate(Utility.translationVector(currentPoint, p));
                }
            }
            currentPoint = p;
        } else if (somethingSelected){ //pomera se jedan element
            if (current==null)
                return;
            ((Interclass)current.getElement()).setBasedOnCenterpoint(p);
        } else { //pomera se sam panel
            diagramView.moveView(Utility.translationVector(currentPoint, p).x, Utility.translationVector(currentPoint, p).getY());
        }

    }

    @Override
    public void misOtpusten(Point p, DiagramView diagramView) {

        List<ElementPainter> selectedList = diagramView.getSelectedPainters();
        MoveCommand moveCommand = new MoveCommand(diagramView, p, currentPoint, initialPoint, somethingSelected, current, selectedList);
        diagramView.getCommandManager().addCommand(moveCommand);
        somethingSelected=false;
        current=null;
//        List<ElementPainter> selectedList = diagramView.getSelectedPainters();
//
//
//        if (!selectedList.isEmpty()) { //lista selektovanih
//            for (ElementPainter painter:selectedList) {
//                if (painter instanceof InterclassPainter){
//                    ((Interclass)painter.getElement()).translate(translationVector(currentPoint, p));
//                }
//            }
//            somethingSelected = false;
//        }else if (somethingSelected){ //pojedinacni element
//            if (current == null)
//                return;
//            ((Interclass) current.getElement()).setBasedOnCenterpoint(p);
//            current = null;
//            somethingSelected = false;
//        } else { //ceo panel
//            diagramView.moveView(translationVector(initialPoint, p).x, translationVector(currentPoint, p).getY());
//            somethingSelected = false;
//        }
    }


}
