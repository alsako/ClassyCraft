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
        copyContent(duplicate);
        return duplicate;
    }
}
