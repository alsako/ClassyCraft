package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.commands.DeleteCommand;
import raf.dsw.classycraft.app.gui.swing.view.painters.*;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.connections.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteElementState implements ClassyState{
    @Override
    public void misKliknut(Point p, DiagramView diagramView) {

        DeleteCommand deleteCommand = new DeleteCommand(p, diagramView, diagramView.getSelectedPainters());
        diagramView.getCommandManager().addCommand(deleteCommand);

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
