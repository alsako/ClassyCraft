package raf.dsw.classycraft.app.model.modelImpl.connections;

import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import java.awt.*;

public class Zavisnost extends Connection{


    public Zavisnost(String name, ClassyNode parent, Interclass from) {
        super(name, parent, new Color(97, 154, 206, 140), from);
        this.setTypeSign("d");
    }
}
