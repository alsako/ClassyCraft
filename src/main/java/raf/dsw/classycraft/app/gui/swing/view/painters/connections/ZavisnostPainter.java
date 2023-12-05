package raf.dsw.classycraft.app.gui.swing.view.painters.connections;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.connections.Zavisnost;

import java.awt.*;
import java.awt.geom.Line2D;

public class ZavisnostPainter extends ConnectionPainter {


    public ZavisnostPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Zavisnost zavisnost = (Zavisnost) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;

        if (zavisnost.getToElement()==null) {
            this.startX = zavisnost.getFromElement().getCenterCoordinates().x;
            this.startY = zavisnost.getFromElement().getCenterCoordinates().y;
            this.endX = zavisnost.getX();
            this.endY = zavisnost.getY();
        } else {
            Point[] points = zavisnost.getLineCoordinates();
            startX=points[0].x;
            startY=points[0].y;
            endX=points[1].x;
            endY=points[1].y;
        }

        Line2D.Double line = new Line2D.Double(startX, startY, endX, endY);
        setShape(line);

        float[] dashPattern = {5, 5};
        BasicStroke dashedStroke = new BasicStroke(zavisnost.getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dashPattern, 0);

        g2D.setStroke(dashedStroke);
        g2D.setColor(zavisnost.getColourOutline());

        g2D.draw(getShape());
    }

}
