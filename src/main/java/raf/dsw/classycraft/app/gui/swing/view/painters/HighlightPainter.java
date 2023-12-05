package raf.dsw.classycraft.app.gui.swing.view.painters;

import lombok.Setter;

import java.awt.*;
@Setter
public class HighlightPainter extends ElementPainter{

    ElementPainter basePainter;

    public HighlightPainter(ElementPainter basePainter) {
        this.basePainter = basePainter;
        this.setShape(basePainter.getShape());
    }

    @Override
    public void draw(Graphics g) {
        update();
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(new Color(243, 200, 17, 77));
        g2D.fill(getShape());
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(new Color(243, 200, 17, 255));
        g2D.draw(getShape());
    }

    void update() {
        this.setShape(basePainter.getShape());
    }


    @Override
    public boolean elementAt(int x, int y) {
        return false;
    }
}
