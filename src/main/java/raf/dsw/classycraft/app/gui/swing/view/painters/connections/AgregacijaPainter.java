package raf.dsw.classycraft.app.gui.swing.view.painters.connections;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.connections.Agregacija;

import java.awt.*;
import java.awt.geom.Line2D;

public class AgregacijaPainter extends ConnectionPainter {

    public AgregacijaPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Agregacija a = (Agregacija) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;

        if (a.getToElement()==null) {
            this.startX = a.getFromElement().getCenterCoordinates().x;
            this.startY = a.getFromElement().getCenterCoordinates().y;
            this.endX = a.getX();
            this.endY = a.getY();
        } else {
          Point[] points = a.getLineCoordinates();
          startX=points[0].x;
          startY=points[0].y;
          endX=points[1].x;
          endY=points[1].y;
        }


        Line2D.Double line = new Line2D.Double(startX, startY, endX, endY);
        setShape(line);

        g2D.setStroke(new BasicStroke(a.getStrokeWidth()));
        g2D.setColor(a.getColourOutline());

        g2D.draw(getShape());
    }

}
