package raf.dsw.classycraft.app.gui.swing.view.painters.connections;

import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;

import java.awt.*;

public class ConnectionPainter extends ElementPainter {

    public double startX, startY, endX, endY;


    public ConnectionPainter(DiagramElement element) {
        super(element);
    }
    public ConnectionPainter() {
    }

    @Override
    public void draw(Graphics g) {

    }
    @Override
    public boolean elementAt(int x, int y) {
        if(getShape() == null) return false;
        return getShape().getBounds2D().contains(x, y);
    }
}
