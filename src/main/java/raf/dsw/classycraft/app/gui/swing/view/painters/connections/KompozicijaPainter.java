package raf.dsw.classycraft.app.gui.swing.view.painters.connections;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.connections.Kompozicija;

import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class KompozicijaPainter extends ConnectionPainter {

    public KompozicijaPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Kompozicija kompozicija = (Kompozicija) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;

        g2D.setStroke(new BasicStroke(kompozicija.getStrokeWidth()));
        g2D.setColor(kompozicija.getColourOutline());

        if (kompozicija.getToElement()==null) {
            this.startX = kompozicija.getFromElement().getCenterCoordinates().x;
            this.startY = kompozicija.getFromElement().getCenterCoordinates().y;
            this.endX = kompozicija.getX();
            this.endY = kompozicija.getY();
        } else if (!(kompozicija.getToElement().equals(kompozicija.getFromElement()))){
            Point[] points = kompozicija.getLineCoordinates();
            if (points==null || points[0]==null || points[1]==null)
                return;
            startX=points[0].x;
            startY=points[0].y;
            endX=points[1].x;
            endY=points[1].y;
        } else{
            Point[] connectionPoints = kompozicija.getFromElement().getReflexiveConnectionPoints();
            Point[] rhomboidPoints = Utility.calculateRhomboidPoints(connectionPoints[0].x, connectionPoints[0].y, connectionPoints[1].x, connectionPoints[1].y);
            GeneralPath path = new GeneralPath();
            path.moveTo(rhomboidPoints[2].x, rhomboidPoints[2].y);
            for (int i=1; i<connectionPoints.length; i++){
                path.lineTo(connectionPoints[i].x, connectionPoints[i].y);
            }
            setShape(path);
            g2D.draw(getShape());
            Polygon polygon = new Polygon();
            for (Point p:rhomboidPoints) {
                polygon.addPoint(p.x, p.y);
            }
            g2D.draw(polygon);
            g2D.fill(polygon);
            return;
        }

        Point direction = Utility.normalizedDirectionVector(startX, startY, endX, endY);
        Line2D.Double line = new Line2D.Double(startX+direction.x, startY+direction.y, endX, endY);
        setShape(line);
        g2D.draw(getShape());

        Point[] rhomboidPoints = Utility.calculateRhomboidPoints(startX, startY, endX, endY);
        Polygon polygon = new Polygon();
        for (Point p:rhomboidPoints) {
            polygon.addPoint(p.x, p.y);
        }
        g2D.draw(polygon);
        g2D.fill(polygon);

    }

}
