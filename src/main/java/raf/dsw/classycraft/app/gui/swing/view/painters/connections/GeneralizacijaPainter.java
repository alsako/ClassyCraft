package raf.dsw.classycraft.app.gui.swing.view.painters.connections;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.connections.Generalizacija;

import java.awt.*;
import java.awt.geom.Line2D;

public class GeneralizacijaPainter extends ConnectionPainter {

    public GeneralizacijaPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Generalizacija generalizacija = (Generalizacija) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;


        if (generalizacija.getToElement()==null) {
            this.startX = generalizacija.getFromElement().getCenterCoordinates().x;
            this.startY = generalizacija.getFromElement().getCenterCoordinates().y;
            this.endX = generalizacija.getX();
            this.endY = generalizacija.getY();
        } else {
            Point[] points = generalizacija.getLineCoordinates();
            startX=points[0].x;
            startY=points[0].y;
            endX=points[1].x;
            endY=points[1].y;
        }

        Line2D.Double line = new Line2D.Double(startX, startY, endX, endY);
        setShape(line);

        g2D.setStroke(new BasicStroke(generalizacija.getStrokeWidth()));
        g2D.setColor(generalizacija.getColourOutline());

        g2D.draw(getShape());
    }

}
