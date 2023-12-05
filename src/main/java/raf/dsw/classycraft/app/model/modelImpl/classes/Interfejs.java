package raf.dsw.classycraft.app.model.modelImpl.classes;

import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import java.awt.*;

public class Interfejs extends Interclass {

    public Interfejs(String name, ClassyNode parent, double x, double y) {

        super(name, parent, x, y);
    }

    @Override
    public Interclass duplicate() {
        Interfejs duplicate = new Interfejs(this.getName(), this.getParent(), this.getX()+10, this.getY()+10);
        duplicate.setHeight(this.getHeight());
        duplicate.setWidth(this.getWidth());
        duplicate.setClassContentList(this.getClassContentList());
        duplicate.setVisibility(this.isVisibility());
        duplicate.setColourInside(this.getColourInside());
        duplicate.setStrokeWidth(this.getStrokeWidth());
        duplicate.setColourOutline(this.getColourOutline());
        return duplicate;
    }
}
