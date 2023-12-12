package raf.dsw.classycraft.app.model.modelImpl.connections;

import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import java.awt.*;

public class Kompozicija extends Connection{


    public Kompozicija(String name, ClassyNode parent, Interclass from) {
        super(name, parent, Color.black, from);
        this.setTypeSign("c");
    }

}
