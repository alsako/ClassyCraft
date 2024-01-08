package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.commands.ChangeContentCommand;
import raf.dsw.classycraft.app.controller.MakeNewMethod;
import raf.dsw.classycraft.app.controller.MakeNewAttribute;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.optionframes.InterclassContentWindow;
import raf.dsw.classycraft.app.gui.swing.view.painters.*;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ChangeContentState implements ClassyState{

    @Override
    public void misKliknut(Point p, DiagramView diagramView) {

        ChangeContentCommand changeContentCommand = new ChangeContentCommand(diagramView, p);
        diagramView.getCommandManager().addCommand(changeContentCommand);

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
