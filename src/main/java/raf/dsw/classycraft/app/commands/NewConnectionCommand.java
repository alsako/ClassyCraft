package raf.dsw.classycraft.app.commands;


import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.painters.*;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.connections.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NewConnectionCommand extends AbstractCommand{

    private DiagramView diagramView;

    private ConnectionPainter currPainter;

    private ConnectionPainter cp;
    private Point p;

    public NewConnectionCommand(Point p, DiagramView diagramView, ConnectionPainter cp) {
        this.diagramView = diagramView;
        this.p = p;
        this.cp = cp;
    }

    @Override
    public void doCommand() {

        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());
        //diagramPainters.remove(diagramPainters.size() - 1); //skida poslednji u slucaju da se ne zavrsava na validnom mestu
        //diagramView.repaint(); //pozivam repaint u slucaju da veza nije zavrsena na dobrom mestu da ne ostaje

        if(currPainter == null) {
            for (ElementPainter painter : diagramPainters) {
                if (painter instanceof InterclassPainter && painter.elementAt(p.x, p.y)) {
                    if (painter.getElement().equals(((Connection) cp.getElement()).getFromElement())) {
                        Interclass to = ((Connection) cp.getElement()).getFromElement();
                        ((Connection) cp.getElement()).setToElement(to);
                        ((Connection) cp.getElement()).setName(((Connection) cp.getElement()).getTypeSign() + ": reflexive " + to.getName());
                        MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram()).remove(cp);
                        MainFrame.getInstance().getPackageView().addPainterToMap(cp);
                        currPainter = cp;
                        this.cp = null;
                        return;
                    }
                    Interclass to = (Interclass) painter.getElement();
                    Interclass from = ((Connection) cp.getElement()).getFromElement();
                    ((Connection) cp.getElement()).setToElement(to);
                    ((Connection) cp.getElement()).setName(((Connection) cp.getElement()).getTypeSign() + ": " + from.getName() + "-" + to.getName());
                    MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram()).remove(cp);
                    MainFrame.getInstance().getPackageView().addPainterToMap(cp);
                    currPainter = cp;
                    this.cp = null;
                    return;
                }
            }

//            diagramPainters.remove(diagramPainters.size()-1);
//            diagramView.repaint();
            diagramView.getCommandManager().removeLastCommand();
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.CONNECTION_MUST_END_IN_ENTITY);

        }else{
            //currPainter.getElement().addSubscriber(diagramView);
            MainFrame.getInstance().getPackageView().addPainterToMap(currPainter);
        }
    }


    @Override
    public void undoCommand() {
        if (currPainter != null){
            MainFrame.getInstance().getPackageView().removePainterFromMap(currPainter);
        }
    }
}
