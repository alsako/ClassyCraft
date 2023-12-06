package raf.dsw.classycraft.app.gui.swing.view.painters.connections;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.connections.Kompozicija;

import java.awt.*;
import java.awt.geom.Line2D;

public class KompozicijaPainter extends ConnectionPainter {

    public KompozicijaPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Kompozicija kompozicija = (Kompozicija) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;


        if (kompozicija.getToElement()==null) {
            this.startX = kompozicija.getFromElement().getCenterCoordinates().x;
            this.startY = kompozicija.getFromElement().getCenterCoordinates().y;
            this.endX = kompozicija.getX();
            this.endY = kompozicija.getY();
        } else if (!(kompozicija.getToElement().equals(kompozicija.getFromElement()))){
            Point[] points = kompozicija.getLineCoordinates();
            startX=points[0].x;
            startY=points[0].y;
            endX=points[1].x;
            endY=points[1].y;
        } else return;


        Line2D.Double line = new Line2D.Double(startX, startY, endX, endY);
        setShape(line);

        g2D.setStroke(new BasicStroke(kompozicija.getStrokeWidth()));
        g2D.setColor(kompozicija.getColourOutline());

        g2D.draw(getShape());
    }

}
