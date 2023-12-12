package raf.dsw.classycraft.app.gui.swing.view.painters.connections;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.connections.Zavisnost;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class ZavisnostPainter extends ConnectionPainter {


    public ZavisnostPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Zavisnost zavisnost = (Zavisnost) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;

        float[] dashPattern = {5, 5};
        BasicStroke dashedStroke = new BasicStroke(zavisnost.getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dashPattern, 0);

        g2D.setStroke(dashedStroke);
        g2D.setColor(zavisnost.getColourOutline());


        if (zavisnost.getToElement()==null) {
            this.startX = zavisnost.getFromElement().getCenterCoordinates().x;
            this.startY = zavisnost.getFromElement().getCenterCoordinates().y;
            this.endX = zavisnost.getX();
            this.endY = zavisnost.getY();
        } else if (!(zavisnost.getFromElement().equals(zavisnost.getToElement()))){
            Point[] points = zavisnost.getLineCoordinates();
            if (points==null || points[0]==null || points[1]==null)
                return;
            startX=points[0].x;
            startY=points[0].y;
            endX=points[1].x;
            endY=points[1].y;
        } else{
            Point[] connectionPoints = zavisnost.getFromElement().getReflexiveConnectionPoints();
            Point[] trianglePoints = Utility.calculateTrianglePoints(connectionPoints[0].x, connectionPoints[0].y, connectionPoints[1].x, connectionPoints[1].y);
            GeneralPath path = new GeneralPath();
            path.moveTo(connectionPoints[0].x, connectionPoints[0].y);
            for (Point p:connectionPoints) {
                path.lineTo(p.x, p.y);
            }
            setShape(path);
            g2D.draw(getShape());
            g2D.setStroke(new BasicStroke(zavisnost.getStrokeWidth()));
            g2D.draw(new Line2D.Double(trianglePoints[0].x, trianglePoints[0].y, trianglePoints[1].x, trianglePoints[1].y));
            g2D.draw(new Line2D.Double(trianglePoints[2].x, trianglePoints[2].y, trianglePoints[1].x, trianglePoints[1].y));
            return;
        }

        Line2D.Double line = new Line2D.Double(startX, startY, endX, endY);
        setShape(line);
        g2D.draw(getShape());

        Point[] trianglePoints = Utility.calculateTrianglePoints(endX, endY,startX, startY);
        g2D.setStroke(new BasicStroke(zavisnost.getStrokeWidth()));
        g2D.draw(new Line2D.Double(trianglePoints[0].x, trianglePoints[0].y, trianglePoints[1].x, trianglePoints[1].y));
        g2D.draw(new Line2D.Double(trianglePoints[2].x, trianglePoints[2].y, trianglePoints[1].x, trianglePoints[1].y));
    }


}
