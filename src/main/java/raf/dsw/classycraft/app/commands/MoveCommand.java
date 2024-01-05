package raf.dsw.classycraft.app.commands;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.Utility;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import java.awt.*;
import java.awt.geom.NoninvertibleTransformException;
import java.util.List;

public class MoveCommand extends AbstractCommand{
    private DiagramView diagramView;

    private Point p;
    private Point currentPoint;
    private Point initialPoint;
    private Boolean somethingSelected;
    private ElementPainter current;
    private Point inverseTranslationVector;

    private double revertX;
    private double revertY;

    public MoveCommand(DiagramView diagramView, Point p, Point currentPoint, Point initialPoint, Boolean somethingSelected, ElementPainter current) {
        this.diagramView = diagramView;
        this.p = p;
        this.currentPoint = currentPoint;
        this.initialPoint = initialPoint;
        this.somethingSelected = somethingSelected;
        this.current = current;
    }

    @Override
    public void doCommand() {
        List<ElementPainter> selectedList = diagramView.getSelectedPainters();

        revertX = initialPoint.getX();
        revertY = initialPoint.getY();

        if (!selectedList.isEmpty()) { //lista selektovanih
            for (ElementPainter painter:selectedList) {
                if (painter instanceof InterclassPainter){
                    ((Interclass)painter.getElement()).translate(Utility.translationVector(currentPoint, p));
                    inverseTranslationVector = Utility.translationVector(p, currentPoint);
                }
            }
        }else if (somethingSelected){ //pojedinacni element
            if (current == null)
                return;
            ((Interclass) current.getElement()).setBasedOnCenterpoint(p);
        } else { //ceo panel
            diagramView.moveView(Utility.translationVector(currentPoint, p).x, Utility.translationVector(currentPoint, p).getY());
        }
    }

    @Override
    public void undoCommand() throws NoninvertibleTransformException {
        List<ElementPainter> selectedList = diagramView.getSelectedPainters();

        if (!selectedList.isEmpty()) { //lista selektovanih
            for (ElementPainter painter:selectedList) {
                if (painter instanceof InterclassPainter){
                    ((Interclass)painter.getElement()).translate(inverseTranslationVector);
                }
            }
        }else if (somethingSelected){ //pojedinacni element
            ((Interclass)current.getElement()).translateTo(new Point((int) revertX, (int)revertY));
        }
    }



}
