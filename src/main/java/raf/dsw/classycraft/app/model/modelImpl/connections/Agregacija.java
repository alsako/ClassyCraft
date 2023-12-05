package raf.dsw.classycraft.app.model.modelImpl.connections;


import lombok.Getter;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import java.awt.*;
@Getter
public class Agregacija extends Connection {


    public Agregacija(String name, ClassyNode parent, Interclass from) {
        super(name, parent, new Color(169, 51, 51), from);
        this.setTypeSign("a");
    }
}
