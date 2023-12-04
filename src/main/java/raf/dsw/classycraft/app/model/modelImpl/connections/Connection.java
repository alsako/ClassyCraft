package raf.dsw.classycraft.app.model.modelImpl.connections;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.painters.InterclassPainter;
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

    public Point getFromCoordinates(){
        Point p = new Point();
        p.setLocation(fromElement.getX(), fromElement.getY());
        return p;
    }

    public Point getToCoordinates(){
        Point p = new Point();
        p.setLocation(toElement.getX(), toElement.getY());
        return p;
    }

    public List<Interclass> getAssociatedInterclasses(){
        List<Interclass> classes = new ArrayList<>();
        classes.add(fromElement);
        classes.add(toElement);
        return  classes;
    }


}
