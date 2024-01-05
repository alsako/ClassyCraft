package raf.dsw.classycraft.app.gui.swing.view.painters;

import lombok.Getter;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SelectionPainter extends ElementPainter implements IPublisher {

    private double startX, startY, width, height;
    private transient List<ISubscriber> subscribers;


    public SelectionPainter(double x, double y) {
        super(null);
        this.startX = x;
        this.startY = y;
        this.width = 0;
        this.height = 0;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        this.setShape(new Rectangle2D.Double(startX, startY, width, height));
        g2D.setStroke(new BasicStroke(2));
        g2D.setColor(new Color(73, 72, 72, 68));
        g2D.fill(getShape());
        g2D.setColor(new Color(73, 72, 72));
        g2D.draw(getShape());
    }

    public void calculateCoordinates(double initialX, double initialY, double finalX, double finalY) {
        this.width = Math.abs(initialX - finalX);
        this.height = Math.abs(initialY - finalY);
        if (finalX > initialX && finalY > initialY) {
            this.startX = initialX;
            this.startY = initialY;
        } else if (finalX > initialX && finalY < initialY) {
            this.startX = initialX;
            this.startY = initialY - height;
        } else if (finalX < initialX && finalY < initialY) {
            this.startX = initialX - width;
            this.startY = initialY - height;
        } else {
            this.startX = initialX - width;
            this.startY = initialY;
        }
        notifySubscribers(DiagramNtfType.REPAINT);
    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if (sub == null)
            return;
        if (this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if (this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if (sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object diagramNotification) {
        if (diagramNotification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (ISubscriber sub : subscribers) {
            sub.update(diagramNotification);
        }
    }
}
