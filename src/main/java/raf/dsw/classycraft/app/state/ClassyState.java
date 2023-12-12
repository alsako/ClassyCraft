package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;

public interface ClassyState {

    void misKliknut(Point p, DiagramView diagramView);
    void misPrevucen(Point p, DiagramView diagramView);
    void misOtpusten(Point p, DiagramView diagramView);

}
