package raf.dsw.classycraft.app.model.modelImpl.classes;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.Project;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Atribut;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.ClassContent;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Metoda;
import raf.dsw.classycraft.app.model.modelImpl.connections.Agregacija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Generalizacija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Kompozicija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Zavisnost;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@JsonSubTypes({
        @JsonSubTypes.Type(value = Klasa.class, name = "Klasa"),
        @JsonSubTypes.Type(value = Enum.class, name = "Enum"),
        @JsonSubTypes.Type(value = Interfejs.class, name = "Interfejs")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Interclass extends DiagramElement{

    private static int lastId = 0;
    private int id;
    private VisibilityTypes visibility;
    private double width, height;
    private LinkedList<ClassContent> classContentList;
    private List<ClassContent> additionOrder = new ArrayList<>();


    public Interclass(String name, ClassyNode parent, double x, double y) {
        super(name, parent, Color.BLACK, x, y);
        this.classContentList = new LinkedList<>();
        width = DiagramView.fontMetrics.stringWidth(name) + 20;
        height = 2 * (DiagramView.fontMetrics.getHeight()) + 10;
        id = ++lastId;
    }

    public Interclass() {
        this.id = ++lastId;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        if (this.getParent()!=null) {
            if (!classContentList.isEmpty()) {
                this.width = calculateMaxWidth(DiagramView.fontMetrics, getContentStrings()) + 20;
            } else width = DiagramView.fontMetrics.stringWidth(name) + 20;
        }
        notifySubscribers(DiagramNtfType.REPAINT);
        changed();
    }

    public void setBasedOnCenterpoint(Point p){
        this.setX(p.x-width/2);
        this.setY(p.y-height/2);
        notifySubscribers(DiagramNtfType.REPAINT);
        changed();
    }

    public void translate(Point delta){
        this.setX(this.getX()+delta.x);
        this.setY(this.getY()+delta.y);
        notifySubscribers(DiagramNtfType.REPAINT);
        changed();
    }

    public void translateTo(Point newPoint){
        this.setX(newPoint.x);
        this.setY(newPoint.y);
        notifySubscribers(DiagramNtfType.REPAINT);
        changed();
    }

    public void addContent(ClassContent c){
        if (c instanceof Atribut)
            this.classContentList.addFirst(c);
        else this.classContentList.addLast(c); // prvo hocu sve atribute pa sve metode
        if(!additionOrder.contains(c))
            additionOrder.add(c);//dodajem klas kontent u listu ako vec ne postoji
        resize();
    }

    public void removeContent(int index){
        this.classContentList.remove(index);
        resize();
    }

    public void resize(){
        this.width = calculateMaxWidth(DiagramView.fontMetrics, getContentStrings()) + 20;
        this.height = (getContentStrings().size() + 2) * (DiagramView.fontMetrics.getHeight()) + 10;
        notifySubscribers(DiagramNtfType.REPAINT);
        changed();
    }
    @JsonIgnore
    public Point getCenterCoordinates(){
        Point point = new Point();
        double x = this.getX()+width/2;
        double y = this.getY() -10 +height/2;
        point.setLocation(x, y);
        return point;
    }
    @JsonIgnore
    public List<String> getContentStrings(){
        List<String> strings = new ArrayList<>();
        for (ClassContent c:classContentList) {
            strings.add(c.toString());
        }
        return strings;
    }

    @JsonIgnore
    public Point[] getReflexiveConnectionPoints(){ // connection pointovi za refleksivne veze
        double x = this.getX();
        double y = this.getY()-10;
        Point p1 = new Point((int)x, (int)(y+height*0.5));
        Point p2 = new Point((int)(x-width*0.4), (int)(y+height*0.5));
        Point p3 = new Point((int)(x-width*0.4), (int)(y+height*1.25));
        Point p4 = new Point((int)(x+width*0.5), (int)(y+height*1.25));
        Point p5 = new Point((int)(x+width*0.5), (int)(y+height));
        Point[] result = new Point[5];
        result[0]=p1;
        result[1]=p2;
        result[2]=p3;
        result[3]=p4;
        result[4]=p5;
        return result;
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

    public int calculateMaxWidth(FontMetrics fontMetrics, List<String> contents) {
        int maxWidth = 0;
        for (String s : contents) {
            int stringWidth = fontMetrics.stringWidth(s);
            if (stringWidth > maxWidth) {
                maxWidth = stringWidth;
            }
        }
        if(fontMetrics.stringWidth(this.getName())>maxWidth)
            maxWidth=fontMetrics.stringWidth(this.getName());
        return maxWidth;
    }

    public abstract Interclass duplicate();

    public void copyContent(Interclass duplicate){
        duplicate.setHeight(this.getHeight());
        duplicate.setWidth(this.getWidth());
        LinkedList<ClassContent> classContentListCopy = new LinkedList<>(this.classContentList);
        duplicate.setClassContentList(classContentListCopy);
        duplicate.setVisibility(this.getVisibility());
        duplicate.setStrokeWidth(this.getStrokeWidth());
    }

    @Override
    public String toString() {
        return "Interclass{" +
                "visibility=" + visibility +
                ", width=" + width +
                ", height=" + height +
                ", classContentList=" + classContentList +
                ", additionOrder=" + additionOrder +
                '}';
    }
}
