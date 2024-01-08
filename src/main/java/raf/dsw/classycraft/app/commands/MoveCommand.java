package raf.dsw.classycraft.app.commands;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.Utility;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import java.awt.*;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.List;

public class MoveCommand extends AbstractCommand{
    private DiagramView diagramView;

    private Point p;
    private Point currentPoint;
    private Point initialPoint;
    private Boolean somethingSelected;
    private ElementPainter current;

    private List<ElementPainter> selectedPainters;
    private List<ElementPainter> affectedPainters = new ArrayList<>();

    private double revertX;
    private double revertY;

    public MoveCommand(DiagramView diagramView, Point p, Point currentPoint, Point initialPoint, Boolean somethingSelected, ElementPainter current,List<ElementPainter> selectedPainters) {
        this.diagramView = diagramView;
        this.p = p;
        this.currentPoint = currentPoint;
        this.initialPoint = initialPoint;
        this.somethingSelected = somethingSelected;
        this.current = current;
        this.selectedPainters = selectedPainters;
    }

    @Override
    public void doCommand() {

        revertX = initialPoint.getX();
        revertY = initialPoint.getY();

        if (!(affectedPainters.isEmpty())){ //ovo je redo za multiselekciju
            for (ElementPainter affectedPainter:affectedPainters) {
                if (affectedPainter instanceof InterclassPainter){
                    ((Interclass)affectedPainter.getElement()).translate(Utility.translationVector(initialPoint, currentPoint));
                }
            }
        }
        if (!selectedPainters.isEmpty()) { //prvi do za selektovane
            for (ElementPainter painter:selectedPainters) {
                if (painter instanceof InterclassPainter){
                    if (!affectedPainters.contains(painter))
                        affectedPainters.add(painter);
                }
            }
        }else if (somethingSelected){ //do i redo za pojedinacni element
            if (current == null)
                return;
            ((Interclass) current.getElement()).setBasedOnCenterpoint(p);
        }

    }

    @Override
    public void undoCommand() throws NoninvertibleTransformException {

        if (!affectedPainters.isEmpty()){
            System.out.println(Utility.translationVector(currentPoint, initialPoint));
            for (ElementPainter painter:affectedPainters) {
                if (painter instanceof InterclassPainter){
                    ((Interclass)painter.getElement()).translate(Utility.translationVector(currentPoint, initialPoint));
                }
            }
        }
        else if (somethingSelected){ //pojedinacni element
            ((Interclass)current.getElement()).translateTo(new Point((int) revertX, (int)revertY));
        }
    }



}
