package raf.dsw.classycraft.app.commands;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.ConnectionPainter;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.connections.Connection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand{

    private Point p;
    private DiagramView diagramView;
    DiagramElement removed = null;

    private List<ElementPainter> deletedPainters = new ArrayList<>();
    private List<ConnectionPainter> toRemove = new ArrayList<>();

    private List<ElementPainter> selectedList = new ArrayList<>();


    public DeleteCommand(Point p, DiagramView diagramView) {
        this.p = p;
        this.diagramView = diagramView;
    }

    @Override
    public void doCommand() {

        selectedList = MainFrame.getInstance().getPackageView().getCurrentDiagramView().getSelectedPainters();

        if(selectedList.isEmpty()){
            //BRISANJE POJEDINACNOG EL PREKO DUGMETA
            List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());

            removed = null;

            for (ElementPainter painter : diagramPainters) {
                if (painter.elementAt(p.x, p.y)) {
                    deletedPainters.add(painter);
                    painter.getElement().removeSubscriber(diagramView);
                    MainFrame.getInstance().getPackageView().removePainterFromMap(painter);
                    removed = painter.getElement();
                    break;
                }
            }


            if (removed instanceof Interclass) {
                for (ElementPainter painter : diagramPainters) {
                    if (painter instanceof ConnectionPainter) {
                        if (((Connection) painter.getElement()).getAssociatedInterclasses().contains(removed)) {
                            toRemove.add((ConnectionPainter) painter);
                        }
                    }
                }
            }

            for (ConnectionPainter painter : toRemove) {
                MainFrame.getInstance().getPackageView().removePainterFromMap(painter);
            }
        }

    }

    @Override
    public void undoCommand() {



            for (ElementPainter p : deletedPainters) {
                MainFrame.getInstance().getPackageView().addPainterToMap(p);
                p.getElement().addSubscriber(diagramView);
            }

            for (ConnectionPainter painter : toRemove) {
                MainFrame.getInstance().getPackageView().addPainterToMap(painter);
            }

            deletedPainters.clear();
            toRemove.clear();

    }
}
