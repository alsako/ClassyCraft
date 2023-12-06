package raf.dsw.classycraft.app.model.modelImpl.classes;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Atribut;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.ClassContent;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Metoda;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Interclass extends DiagramElement{

    private boolean visibility;
    private double width, height;
    private List<ClassContent> classContentList;

    List<ISubscriber> subscribers;


    public Interclass(String name, ClassyNode parent, double x, double y) {

        super(name, parent, Color.BLACK, x, y);
        this.classContentList = new ArrayList<>();
    }

    public void setSize(double w, double h){
        this.width = w;
        this.height = h;
    }

    public Point getCenterCoordinates(){
        Point point = new Point();
        double x = this.getX()+width/2;
        double y = this.getY() -10 +height/2;
        point.setLocation(x, y);
        return point;
    }

    public List<String> getContentStrings(){
        List<String> strings = new ArrayList<>();
        for (ClassContent c:classContentList) {
            if (c instanceof Atribut)
                strings.add(c.toString());
        }
        for (ClassContent c:classContentList) {
            if (c instanceof Metoda)
                strings.add(c.toString());
        }
        return strings;
    }

    public void setBasedOnCenterpoint(Point p){
        this.setX(p.x-width/2);
        this.setY(p.y-height/2);
    }

    public void translate(Point delta){
        this.setX(this.getX()+delta.x);
        this.setY(this.getY()+delta.y);
    }


    public List<Point> connectionPoints(){
        List<Point> list = new ArrayList<>();
        double x = this.getX();
        double y = this.getY()-10;
        Point p1 = new Point((int) (x+width*0.33), (int) y);
        Point p2 = new Point((int) (x+0.66*width), (int) y);
        Point p3 = new Point((int)x, (int)(y+height*0.33));
        Point p4 = new Point((int)x, (int)(y+height*0.66));
        Point p5 = new Point((int)(x+width), (int)(y+height*0.66));
        Point p6 = new Point((int)(x+width), (int)(y+height*0.66));
        Point p7 = new Point((int)(x+width*0.33), (int)(y+height));
        Point p8 = new Point((int)(x+width*0.66), (int)(y+height));
        list.addAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
        return list;
    }

    public abstract Interclass duplicate();

}
