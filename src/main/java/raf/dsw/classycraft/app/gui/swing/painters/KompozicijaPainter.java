package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.connections.Agregacija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Kompozicija;

import java.awt.*;
import java.awt.geom.Line2D;

public class KompozicijaPainter extends ConnectionPainter{

    public KompozicijaPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Kompozicija kompozicija = (Kompozicija) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;

        double startX = kompozicija.getFromCoordinates().x + kompozicija.getFromElement().getWidth()/2;
        double startY = kompozicija.getFromCoordinates().y + kompozicija.getFromElement().getHeight()/2;

        Line2D.Double line = new Line2D.Double(startX, startY, kompozicija.getX(), kompozicija.getY());
        setShape(line);

        g2D.setStroke(new BasicStroke(kompozicija.getStrokeWidth()));
        g2D.setColor(kompozicija.getColourOutline());

        g2D.draw(getShape());
    }

}
