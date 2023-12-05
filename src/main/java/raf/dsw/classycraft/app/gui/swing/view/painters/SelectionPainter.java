package raf.dsw.classycraft.app.gui.swing.view.painters;

import lombok.Getter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
@Getter
public class SelectionPainter extends ElementPainter{

    private double startX, startY, width, height;

    public SelectionPainter(double x, double y) {
        super(null);
        this.startX=x;
        this.startY=y;
        this.width=0;
        this.height=0;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        this.setShape(new Rectangle2D.Double(startX, startY, width, height));
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(new Color(73, 72, 72, 68));
        g2D.fill(getShape());
        g2D.setColor(new Color(73, 72, 72));
        g2D.draw(getShape());
    }

    public void calculateCoordinates(double initialX, double initialY, double finalX, double finalY){
        this.width = Math.abs(initialX-finalX);
        this.height = Math.abs(initialY-finalY);
        if (finalX>initialX && finalY>initialY){
            this.startX=initialX;
            this.startY=initialY;
        } else if (finalX>initialX && finalY<initialY) {
            this.startX=initialX;
            this.startY=initialY-height;
        } else if (finalX<initialX && finalY<initialY) {
            this.startX=initialX-width;
            this.startY=initialY-height;
        }else{
            this.startX=initialX-width;
            this.startY=initialY;
        }
    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }
}
