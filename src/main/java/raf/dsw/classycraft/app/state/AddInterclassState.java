package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.commands.NewInterClassCommand;
import raf.dsw.classycraft.app.controller.actionsImpl.NewInterclassAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.EnumPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterfejsPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.KlasaPainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelImpl.classes.Enum;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.Klasa;

import javax.swing.*;
import java.awt.*;

public class AddInterclassState implements ClassyState {

    @Override
    public void misKliknut(Point p, DiagramView diagramView) {

        NewInterClassCommand newInterClassCommand = new NewInterClassCommand(p, diagramView);
        diagramView.getCommandManager().addCommand(newInterClassCommand);
//        String nameInput = JOptionPane.showInputDialog(new JFrame(), "Enter class name", "Name entry", JOptionPane.PLAIN_MESSAGE);
//
//        if (nameInput==null || nameInput.trim().isEmpty()) {
//            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.NAME_CANNOT_BE_EMPTY);
//            return;
//        }
//        if (NewInterclassAction.selectedOption==null) {
//            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.OPTION_NOT_SELECTED);
//            return;
//        }
//
//        if (NewInterclassAction.selectedOption.equalsIgnoreCase("class")){
//            Klasa klasa = new Klasa(nameInput, diagramView.getDiagram(), p.x, p.y);
//            ElementPainter painter = new KlasaPainter(klasa);
//            klasa.addSubscriber(diagramView);
//            MainFrame.getInstance().getPackageView().addPainterToMap(painter);
//        } else if (NewInterclassAction.selectedOption.equalsIgnoreCase("interface")) {
//            Interfejs interfejs = new Interfejs(nameInput, diagramView.getDiagram(), p.x, p.y);
//            ElementPainter painter = new InterfejsPainter(interfejs);
//            interfejs.addSubscriber(diagramView);
//            MainFrame.getInstance().getPackageView().addPainterToMap(painter);
//        } else if (NewInterclassAction.selectedOption.equalsIgnoreCase("enum")) {
//            Enum en = new Enum(nameInput, diagramView.getDiagram(), p.x, p.y);
//            ElementPainter painter = new EnumPainter(en);
//            en.addSubscriber(diagramView);
//            MainFrame.getInstance().getPackageView().addPainterToMap(painter);
//        }
    }

    @Override
    public void misPrevucen(Point p, DiagramView diagramView) {
        //ne treba
    }

    @Override
    public void misOtpusten(Point p, DiagramView diagramView) {
        //ne treba
    }
}
