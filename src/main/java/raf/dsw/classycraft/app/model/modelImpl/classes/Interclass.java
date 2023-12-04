package raf.dsw.classycraft.app.model.modelImpl.classes;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Interclass extends DiagramElement {

    private boolean visibility;
    private double width, height;
    private List<ClassContent> classContentList;

    public Interclass(String name, ClassyNode parent, double x, double y) {

        super(name, parent, Color.BLACK, x, y);
        this.classContentList = new ArrayList<>();
    }

    public void setSize(double w, double h){
        this.width = w;
        this.height = h;
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
}
