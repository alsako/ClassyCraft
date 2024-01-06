package raf.dsw.classycraft.app.model.modelImpl.classes;

import lombok.NoArgsConstructor;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;

import java.awt.*;
@NoArgsConstructor
public class Enum extends Interclass {

    public Enum(String name, ClassyNode parent, double x, double y) {
        super(name, parent, x, y);
    }

    @Override
    public Interclass duplicate() {
        Enum duplicate = new Enum(this.getName(), this.getParent(), this.getX()+10, this.getY()+10);
        copyContent(duplicate);
        return duplicate;
    }
}
