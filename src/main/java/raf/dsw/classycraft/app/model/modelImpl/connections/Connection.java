package raf.dsw.classycraft.app.model.modelImpl.connections;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public abstract class Connection extends DiagramElement {

    private String typeSign;
    private Interclass fromElement;
    private Interclass toElement;

    public Connection(String name, ClassyNode parent, Color colorOutline, Interclass from) {
        super(name, parent, colorOutline);
        this.fromElement = from;
    }

    public List<Interclass> getAssociatedInterclasses(){
        List<Interclass> classes = new ArrayList<>();
        classes.add(fromElement);
        classes.add(toElement);
        return  classes;
    }

    public Point[] getLineCoordinates(){

        return findMinDistancePoints(fromElement, toElement);

    }

    public Point[] findMinDistancePoints(Interclass i1, Interclass i2) {
        List<Point> list1 = i1.connectionPoints();
        List<Point> list2 = i2.connectionPoints();

        if (list1.isEmpty() || list2.isEmpty()) {
            return null;
        }

        Point[] result = new Point[2];
        double minDistance = pointDistance(list1.get(0), list2.get(0));

        for (Point p1 : list1) {
            for (Point p2 : list2) {
                double distance = pointDistance(p1, p2);
                if (distance < minDistance) {
                    minDistance = distance;
                    result[0] = p1;
                    result[1] = p2;
                }
            }
        }

        return result;
    }
    private double pointDistance(Point p1, Point p2){
        double deltaX = p2.getX() - p1.getX();
        double deltaY = p2.getY() - p1.getY();

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }




}
