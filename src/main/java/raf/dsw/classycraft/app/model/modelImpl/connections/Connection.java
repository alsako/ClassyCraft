package raf.dsw.classycraft.app.model.modelImpl.connections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.Project;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Atribut;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Metoda;
import raf.dsw.classycraft.app.model.modelImpl.classes.Enum;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.Klasa;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@JsonSubTypes({
        @JsonSubTypes.Type(value = Agregacija.class, name = "Agregacija"),
        @JsonSubTypes.Type(value = Generalizacija.class, name = "Generalizacija"),
        @JsonSubTypes.Type(value = Kompozicija.class, name = "Kompozicija"),
        @JsonSubTypes.Type(value = Zavisnost.class, name = "Zavisnost")
})
public abstract class Connection extends DiagramElement {

    private String typeSign;
    private Interclass fromElement;
    private Interclass toElement;

    public Connection(String name, ClassyNode parent, Color colorOutline, Interclass from) {
        super(name, parent, colorOutline);
        this.fromElement = from;
    }

    public void setFromElement(Interclass fromElement) {
        this.fromElement = fromElement;
        notifySubscribers(DiagramNtfType.REPAINT);
    }

    public void setToElement(Interclass toElement) {
        this.toElement = toElement;
        notifySubscribers(DiagramNtfType.REPAINT);
    }

    @JsonIgnore
    public List<Interclass> getAssociatedInterclasses(){
        List<Interclass> classes = new ArrayList<>();
        classes.add(fromElement);
        classes.add(toElement);
        return  classes;
    }

    @JsonIgnore
    public Point[] getLineCoordinates(){
        return findMinDistancePoints(fromElement, toElement);
    }

    public Point[] findMinDistancePoints(Interclass i1, Interclass i2) { //odredjuje connection pointove
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


    @Override
    public String toString() {
        return "Connection{" +
                "typeSign='" + typeSign + '\'' +
                ", fromElement=" + fromElement +
                ", toElement=" + toElement +
                '}';
    }
}
