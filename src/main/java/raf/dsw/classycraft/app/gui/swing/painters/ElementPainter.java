package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;

import java.awt.*;
@Getter
@Setter
public abstract class ElementPainter {

    private DiagramElement element;
    private Shape shape;

    public ElementPainter(DiagramElement element) {
        this.element = element;
    }

    public ElementPainter() {
    }

    public abstract void draw(Graphics g);


    public abstract boolean elementAt(int x, int y);

}
