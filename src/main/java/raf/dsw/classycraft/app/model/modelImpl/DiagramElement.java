package raf.dsw.classycraft.app.model.modelImpl;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class DiagramElement extends ClassyNode implements IPublisher{

    private int strokeWidth;
    private Color colourInside;
    private Color colourOutline;
    private double x, y;

    private List<ISubscriber> subscribers;


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


    public void setX(double x) {
        this.x = x;
        notifySubscribers(DiagramNtfType.REPAINT);
    }

    public void setY(double y) {
        this.y = y;
        notifySubscribers(DiagramNtfType.REPAINT);
    }


    public void addToTree(DiagramView diagramView){
        ((ClassyTreeImpl) MainFrame.getInstance().getClassyTree()).addToTree(diagramView.getDiagram(), this);
    }

    public void removeFromTree(){
        ((ClassyTreeImpl)MainFrame.getInstance().getClassyTree()).removeFromTree(this);
    }


    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    public void removeAllSubscribers(){
        if (this.subscribers==null)
            return;
        this.subscribers.clear();
    }

    @Override
    public void notifySubscribers(Object diagramNotification) {
        if(diagramNotification == null ||this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (ISubscriber sub: subscribers) {
            sub.update(diagramNotification);
        }
    }
}