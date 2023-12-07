package raf.dsw.classycraft.app.gui.swing.view.painters.connections;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.connections.Generalizacija;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class GeneralizacijaPainter extends ConnectionPainter {

    public GeneralizacijaPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Generalizacija generalizacija = (Generalizacija) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;

        g2D.setStroke(new BasicStroke(generalizacija.getStrokeWidth()));
        g2D.setColor(generalizacija.getColourOutline());

        if (generalizacija.getToElement()==null) {
            this.startX = generalizacija.getFromElement().getCenterCoordinates().x;
            this.startY = generalizacija.getFromElement().getCenterCoordinates().y;
            this.endX = generalizacija.getX();
            this.endY = generalizacija.getY();
        }
        else if (!generalizacija.getToElement().equals(generalizacija.getFromElement())){
            Point[] points = generalizacija.getLineCoordinates();
            if (points==null || points[0]==null || points[1]==null)
                return;
            startX=points[0].x;
            startY=points[0].y;
            endX=points[1].x;
            endY=points[1].y;
        }
        else{
            Point[] connectionPoints = generalizacija.getFromElement().getReflexiveConnectionPoints();
            Point[] trianglePoints = Utility.calculateTrianglePoints(connectionPoints[0].x, connectionPoints[0].y, connectionPoints[1].x, connectionPoints[1].y);
            GeneralPath path = new GeneralPath();
            path.moveTo(trianglePoints[0].x, trianglePoints[1].y);
            for (int i=1; i<connectionPoints.length; i++){
                path.lineTo(connectionPoints[i].x, connectionPoints[i].y);
            }
            setShape(path);
            g2D.draw(getShape());
            Polygon polygon = new Polygon();
            for (Point p:trianglePoints) {
                polygon.addPoint(p.x, p.y);
            }
            g2D.draw(polygon);
            return;
        }

        Point direction = Utility.normalizedDirectionVector(startX, startY, endX, endY);
        Line2D.Double line = new Line2D.Double(startX, startY, endX-direction.x, endY-direction.y);
        setShape(line);
        g2D.draw(getShape());

        Point[] trianglePoints = Utility.calculateTrianglePoints(endX, endY,startX, startY);
        Polygon polygon = new Polygon();
        for (Point p:trianglePoints) {
            polygon.addPoint(p.x, p.y);
        }
        g2D.draw(polygon);

    }

}
