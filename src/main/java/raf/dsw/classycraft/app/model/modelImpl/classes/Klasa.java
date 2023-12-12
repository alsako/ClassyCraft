package raf.dsw.classycraft.app.model.modelImpl.classes;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import java.awt.*;

@Getter
@Setter
public class Klasa extends Interclass {



    public Klasa(String name, ClassyNode parent, double x, double y) {
        super(name, parent, x, y);
    }

    @Override
    public Interclass duplicate() {
        Klasa duplicate = new Klasa(this.getName(), this.getParent(), this.getX()+10, this.getY()+10);
        copyContent(duplicate);
        return duplicate;
    }


}
