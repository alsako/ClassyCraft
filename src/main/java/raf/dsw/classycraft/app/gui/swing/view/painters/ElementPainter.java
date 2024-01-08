package raf.dsw.classycraft.app.gui.swing.view.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Getter
@Setter
public abstract class ElementPainter{
    private DiagramElement element;
    private Shape shape;
    private HighlightPainter highlightPainter;

    public ElementPainter(DiagramElement element) {
        this.element = element;
    }

    public ElementPainter() {
    }

    public abstract void draw(Graphics g);


    public abstract boolean elementAt(int x, int y);


}
