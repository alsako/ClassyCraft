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



        if (generalizacija.getToElement()==null) {
            this.startX = generalizacija.getFromElement().getCenterCoordinates().x;
            this.startY = generalizacija.getFromElement().getCenterCoordinates().y;
            this.endX = generalizacija.getX();
            this.endY = generalizacija.getY();
        }
//        else if (generalizacija.getToElement().equals(generalizacija.getFromElement())){
//
//            Point[] points = generalizacija.getFromElement().getRecursonConnectionPoints();
//            GeneralPath path = new GeneralPath();
//            path.moveTo(points[0].x, points[0].y);
//
//            for (int i=1; i<points.length; i++){
//                path.lineTo(points[i].x, points[i].y);
//
//            }
//
//            this.setShape(path);
//            g2D.setStroke(new BasicStroke(generalizacija.getStrokeWidth()));
//            g2D.setColor(generalizacija.getColourOutline());
//            g2D.draw(getShape());
//            return;
//        }
        else if (!generalizacija.getToElement().equals(generalizacija.getFromElement())){
            Point[] points = generalizacija.getLineCoordinates();
            startX=points[0].x;
            startY=points[0].y;
            endX=points[1].x;
            endY=points[1].y;
        }
        else return;

        Line2D.Double line = new Line2D.Double(startX, startY, endX, endY);
        setShape(line);

        g2D.setStroke(new BasicStroke(generalizacija.getStrokeWidth()));
        g2D.setColor(generalizacija.getColourOutline());

        g2D.draw(getShape());
    }

}
