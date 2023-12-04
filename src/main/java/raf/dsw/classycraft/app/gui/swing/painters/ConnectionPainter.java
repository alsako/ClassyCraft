package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;

import java.awt.*;

public class ConnectionPainter extends ElementPainter {



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
