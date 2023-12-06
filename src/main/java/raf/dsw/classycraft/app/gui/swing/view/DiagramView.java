package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.controller.DiagramViewMouseListener;
import raf.dsw.classycraft.app.gui.swing.view.painters.HighlightPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.SelectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;

    private List<ElementPainter> painters = new ArrayList<>();
    private List<HighlightPainter> highlights = new ArrayList<>();
    private AffineTransform transform = null;
    double scaling = 1.0;
    double xTranslation =0.0;
    double yTranslation =0.0;

    public DiagramView(Diagram diagram) {
        this.diagram = diagram;
        DiagramViewMouseListener dvml = new DiagramViewMouseListener();
        this.addMouseListener(dvml);
        this.addMouseMotionListener(dvml);
    }


    @Override
    public void update(Object notification) {
        int index = MainFrame.getInstance().getPackageView().getTabbedPane().indexOfComponent(this);
        switch (((DiagramNtfType)notification)){
            case DELETE:
                MainFrame.getInstance().getPackageView().getTabbedPane().remove(this);
                if (index>=0)
                    MainFrame.getInstance().getPackageView().getTabs().remove(index);
                break;
            case RENAME:
                if (index>=0)
                    MainFrame.getInstance().getPackageView().getTabbedPane().setTitleAt(index, this.diagram.getName());
                break;
            case REPAINT:
                this.repaint();
        }
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
    }

    public void zoomIn(){
        if (transform==null)
            transform = new AffineTransform();
        double newScaling = scaling * 1.1;
        if(newScaling >= 2) newScaling = 2;
        this.scaling = newScaling;
        setupTransformation(scaling);
        repaint();
    }

    public void zoomOut(){
        if (transform==null)
            transform = new AffineTransform();
        double newScaling = scaling/1.1;
        if(newScaling <= 0.5) newScaling = 0.5;
        this.scaling = newScaling;
        setupTransformation(scaling);
        repaint();
    }

    private void setupTransformation(double scaling){
        if (transform==null)
            transform = new AffineTransform();
        transform.setToIdentity();
        transform.translate(xTranslation, yTranslation);
        transform.scale(scaling, scaling);
        repaint();
    }

    public void moveView(double deltaX, double deltaY){
        this.xTranslation += deltaX;
        this.yTranslation += deltaY;
        setupTransformation(scaling);
    }

    public void reset(){
        if (transform!=null)
            transform.setToIdentity();
        scaling = 1;
        xTranslation = 0;
        yTranslation = 0;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        if (transform!=null)
            graphics2D.transform(transform);
        for (ElementPainter painter:painters) {
            if (painter instanceof ConnectionPainter)
                     painter.draw(graphics2D);
        }
        for (ElementPainter painter:painters) {
            if (painter instanceof InterclassPainter)
                painter.draw(graphics2D);
        }
        for (ElementPainter painter:painters) {
            if (painter instanceof SelectionPainter)
                painter.draw(graphics2D);
        }
        for (ElementPainter painter:highlights)
                painter.draw(graphics2D);

    }
}
