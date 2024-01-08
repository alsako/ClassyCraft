package raf.dsw.classycraft.app.commands;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.connections.Connection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand{

    private Point p;
    private DiagramView diagramView;
    ElementPainter removed = null;
    private List<ConnectionPainter> toRemove = new ArrayList<>();
    private List<ElementPainter> diagramPainters = new ArrayList<>();
    private List<ElementPainter> selectedPainters;
    private List<ElementPainter> affectedPainters = new ArrayList<>();


    public DeleteCommand(Point p, DiagramView diagramView, List<ElementPainter> selectedPainters) {
        this.p = p;
        this.diagramView = diagramView;
        this.selectedPainters = selectedPainters;
    }

    @Override
    public void doCommand() {

        diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());

        if (!(affectedPainters.isEmpty())){ //ovo je redo za multiselekciju
            for (ElementPainter affectedPainter:affectedPainters) {
                affectedPainter.getElement().removeSubscriber(diagramView);
                MainFrame.getInstance().getPackageView().removePainterFromMap(affectedPainter);
                fillToRemove(affectedPainter);
            }
            removeConnections(toRemove);

//            for (ConnectionPainter painter : toRemove) {
//                MainFrame.getInstance().getPackageView().removePainterFromMap(painter);
//            }
        } else if (removed!=null) { //redo za brisanje pojedinacnog elementa
            removed.getElement().removeSubscriber(diagramView);
            MainFrame.getInstance().getPackageView().removePainterFromMap(removed);
            removeConnections(toRemove);

//            for (ConnectionPainter painter : toRemove) {
//                MainFrame.getInstance().getPackageView().removePainterFromMap(painter);
//            }
        } else if(!(selectedPainters.isEmpty())){ //prvi do za multiselekciju
            System.out.println(selectedPainters);
            for (ElementPainter selectedPainter:selectedPainters) {
                affectedPainters.add(selectedPainter);
                selectedPainter.getElement().removeSubscriber(diagramView);
                MainFrame.getInstance().getPackageView().removePainterFromMap(selectedPainter);

                fillToRemove(selectedPainter);

            }
            removeConnections(toRemove);

//            for (ConnectionPainter painter : toRemove) {
//                MainFrame.getInstance().getPackageView().removePainterFromMap(painter);
//            }
            diagramView.deselectAll();
        }
        else {
            //BRISANJE POJEDINACNOG EL NA KLIK
            for (ElementPainter painter : diagramPainters) {
                if (painter.elementAt(p.x, p.y)) {
                    painter.getElement().removeSubscriber(diagramView);
                    MainFrame.getInstance().getPackageView().removePainterFromMap(painter);
                    removed = painter;
                    break;
                }
            }

            fillToRemove(removed);
            removeConnections(toRemove);
//            for (ConnectionPainter painter : toRemove) {
//                MainFrame.getInstance().getPackageView().removePainterFromMap(painter);
//            }
        }

    }

    @Override
    public void undoCommand() {

        if (affectedPainters.isEmpty()){
            MainFrame.getInstance().getPackageView().addPainterToMap(removed);
            removed.getElement().addSubscriber(diagramView);
            for (ConnectionPainter painter : toRemove) { //vraca veze
                MainFrame.getInstance().getPackageView().addPainterToMap(painter);
                painter.getElement().addSubscriber(diagramView);
            }
        }else { //za multiselekciju
            for (ElementPainter p : affectedPainters) { //vraca klase
                MainFrame.getInstance().getPackageView().addPainterToMap(p);
                p.getElement().addSubscriber(diagramView);
            }
            for (ConnectionPainter painter : toRemove) { //vraca veze
                MainFrame.getInstance().getPackageView().addPainterToMap(painter);
            }
        }

    }

    public void fillToRemove(ElementPainter removed){ //puni listu vezama koje treba da se izbrisu
        if (removed instanceof InterclassPainter) {
            for (ElementPainter painter : diagramPainters) {
                if (painter instanceof ConnectionPainter) {
                    if (((Connection) painter.getElement()).getAssociatedInterclasses().contains(removed.getElement())) {
                        if (!(toRemove.contains((ConnectionPainter) painter)) && !(selectedPainters.contains(painter))
                                && !(affectedPainters.contains(painter)))
                             toRemove.add((ConnectionPainter) painter);
                    }
                }
            }
        }
    }

    public void removeConnections(List<ConnectionPainter> toRemove){
        for (ConnectionPainter painter : toRemove) {
            MainFrame.getInstance().getPackageView().removePainterFromMap(painter);
        }
    }



}
