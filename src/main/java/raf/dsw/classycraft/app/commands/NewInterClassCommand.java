package raf.dsw.classycraft.app.commands;

import raf.dsw.classycraft.app.controller.actionsImpl.NewInterclassAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.optionframes.NewInterclassOption;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.EnumPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterfejsPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.KlasaPainter;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.classes.Enum;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.Klasa;
import raf.dsw.classycraft.app.model.modelImpl.connections.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class NewInterClassCommand extends AbstractCommand{

    private DiagramView diagramView;

    private Point p;

    private ElementPainter currPainter;

    public NewInterClassCommand(Point p, DiagramView diagramView) {
        this.p = p;
        this.diagramView = diagramView;
    }

    @Override
    public void doCommand() {


        if(currPainter != null){
            currPainter.getElement().addSubscriber(diagramView);
            MainFrame.getInstance().getPackageView().addPainterToMap(currPainter);
        }else {

            String nameInput = JOptionPane.showInputDialog(new JFrame(), "Enter class name", "Name entry", JOptionPane.PLAIN_MESSAGE);

            if (nameInput == null || nameInput.trim().isEmpty()) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.NAME_CANNOT_BE_EMPTY);
                return;
            }
            if (NewInterclassAction.selectedOption == null) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.OPTION_NOT_SELECTED);
                return;
            }

            if (NewInterclassAction.selectedOption.equalsIgnoreCase("class")) {
                Klasa klasa = new Klasa(nameInput, diagramView.getDiagram(), p.x, p.y);
                currPainter = new KlasaPainter(klasa);
                klasa.addSubscriber(diagramView);
                MainFrame.getInstance().getPackageView().addPainterToMap(currPainter);
            } else if (NewInterclassAction.selectedOption.equalsIgnoreCase("interface")) {
                Interfejs interfejs = new Interfejs(nameInput, diagramView.getDiagram(), p.x, p.y);
                currPainter = new InterfejsPainter(interfejs);
                interfejs.addSubscriber(diagramView);
                MainFrame.getInstance().getPackageView().addPainterToMap(currPainter);
            } else if (NewInterclassAction.selectedOption.equalsIgnoreCase("enum")) {
                Enum en = new Enum(nameInput, diagramView.getDiagram(), p.x, p.y);
                currPainter = new EnumPainter(en);
                en.addSubscriber(diagramView);
                MainFrame.getInstance().getPackageView().addPainterToMap(currPainter);
            }
        }
    }

    @Override
    public void undoCommand() {
//        connToEntity = findConnToEntity();
//        for(ElementPainter p: connToEntity){
//            MainFrame.getInstance().getPackageView().removePainterFromMap(p);
//        }
        diagramView.deselectElement(currPainter);
        if(currPainter != null) {
            MainFrame.getInstance().getPackageView().removePainterFromMap(currPainter);
            currPainter.getElement().removeSubscriber(diagramView);
        }else{
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
        }
    }

//    private List<ElementPainter> findConnToEntity(){
//
//        List<ElementPainter> conns = new ArrayList<>();
//        List<ElementPainter> currPainters = MainFrame.getInstance().getPackageView().getCurrentDiagramView().getPainters();
//
//        for(ElementPainter p: currPainters){
//            if(p instanceof ConnectionPainter){
//                Connection connection = (Connection) p.getElement();
//                DiagramElement element = p.getElement();
//                if(connection.getFromElement().equals(element) || connection.getToElement().equals(element)){
//                    conns.add(p);
//                }
//            }
//        }
//
//        return conns;
//    }
}
