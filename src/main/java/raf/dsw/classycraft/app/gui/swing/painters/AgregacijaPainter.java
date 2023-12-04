package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.connections.Agregacija;

import java.awt.*;
import java.awt.geom.Line2D;

public class AgregacijaPainter extends ConnectionPainter{

    public AgregacijaPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Agregacija a = (Agregacija) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;

        double startX = a.getFromElement().getX() + a.getFromElement().getWidth()/2;
        double startY = a.getFromElement().getY() + a.getFromElement().getHeight()/2;

        Line2D.Double line = new Line2D.Double(startX, startY, a.getX(), a.getY());
        setShape(line);

        g2D.setStroke(new BasicStroke(a.getStrokeWidth()));
        g2D.setColor(a.getColourOutline());

        g2D.draw(getShape());
    }

}
