package raf.dsw.classycraft.app.model.modelImpl;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;

import java.awt.*;

@Getter
@Setter
public abstract class DiagramElement extends ClassyNode {

    private int strokeWidth;
    private Color colourInside;
    private Color colourOutline;
    private double x, y;

    public DiagramElement(String name, ClassyNode parent, Color colourOutline) {
        super(name, parent);
        this.strokeWidth = 2;
        this.colourInside = Color.WHITE;
        this.colourOutline = colourOutline;
    }

    public DiagramElement(String name, ClassyNode parent, Color colourOutline, double x, double y) {
        super(name, parent);
        this.strokeWidth = 2;
        this.colourInside = Color.WHITE;
        this.colourOutline = colourOutline;
        this.x = x;
        this.y = y;
    }



}
