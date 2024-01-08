package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.commands.CommandManager;
import raf.dsw.classycraft.app.gui.swing.controller.DiagramViewMouseListener;
import raf.dsw.classycraft.app.gui.swing.view.painters.HighlightPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.SelectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.connections.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.ClassContent;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;
    private CommandManager commandManager;
    private List<ElementPainter> painters = new ArrayList<>();
    private List<HighlightPainter> highlights = new ArrayList<>(); //painteri za obelezavanje selektovanih elemenata
    private List<ElementPainter> selectedPainters = new ArrayList<>(); //lista selektovanih

    private AffineTransform transform = null;
    public static FontMetrics fontMetrics;
    double scaling = 1.0;
    double xTranslation =0.0;
    double yTranslation =0.0;

    public DiagramView(Diagram diagram) {
        this.diagram = diagram;
        this.commandManager =new CommandManager();
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
        if(newScaling >= 4) newScaling = 4;
        this.scaling = newScaling;
        applyTransformation();
    }

    public void zoomOut(){
        if (transform==null)
            transform = new AffineTransform();
        double newScaling = scaling/1.1;
        if(newScaling <= 0.5) newScaling = 0.5;
        this.scaling = newScaling;
        applyTransformation();
    }

    public void zoomToFit(){
        double height = getHeight();
        double width = getWidth();
        double maxX =0, minX=width, maxY=0, minY=height;
        for (ElementPainter p:painters) {
            if (p instanceof InterclassPainter){
                if (p.getElement().getX()<minX)
                    minX = p.getElement().getX();
                if (p.getElement().getX()+((Interclass)p.getElement()).getWidth()>maxX)
                    maxX = p.getElement().getX()+((Interclass)p.getElement()).getWidth();
                if (p.getElement().getY()-10<minY)
                    minY = p.getElement().getY()-10;
                if (p.getElement().getY()+((Interclass)p.getElement()).getHeight()-10>maxY)
                    maxY = p.getElement().getY()+((Interclass)p.getElement()).getHeight()-10;
            }
        }
        double graphicWidth = maxX-minX;
        double graphicHeight = maxY-minY;
        double ratioWidth = width/graphicWidth;
        double ratioHeight = height/graphicHeight;
        double scaling =0.9*Math.min(ratioHeight, ratioWidth);
        double newCenterX = width/(2*scaling);
        double newCenterY = height/(2*scaling);
        double figureCenterX = (maxX+minX)/2;
        double figureCenterY = (maxY+minY)/2;
        setXTranslation((newCenterX-figureCenterX)*scaling);
        setYTranslation((newCenterY-figureCenterY)*scaling);
        this.scaling = scaling;
        applyTransformation();
    }

    public void reset() throws NoninvertibleTransformException{
        if (transform!=null) {
            transform.invert();
            transform.setToIdentity();
        }
        scaling=1;
        xTranslation=0;
        yTranslation=0;
        repaint();
    }

    //prvo moram da postavim na neutral da bi se primenili trenutni scaling i translacija
    private void applyTransformation(){
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
        applyTransformation();
    }

    public void deselectAll(){
        for (ElementPainter selectedPainter:selectedPainters) {
            selectedPainter.setHighlightPainter(null);
        }
        selectedPainters.clear();
        highlights.clear();
        repaint();
    }

    public void deselectElement(ElementPainter painter){
        highlights.remove(painter.getHighlightPainter());
        painter.setHighlightPainter(null);
        selectedPainters.remove(painter);
        repaint();
    }

    public void subscribeToPainterElements(){
        for (ElementPainter painter : this.painters) {
            if (painter.getElement()!=null) { //selectionPainter nema element
                painter.getElement().removeAllSubscribers(); //skidanje ugasenih diagram viewova
                painter.getElement().addSubscriber(this);
            }
            if (painter.getElement() instanceof Interclass){ // subscribuje se na njegove metode i atribute
                List<ClassContent> classContents = ((Interclass) painter.getElement()).getClassContentList();
                if (classContents!=null){
                    for (ClassContent classContent:classContents) {
                        classContent.removeAllSubscribers();
                        classContent.addSubscriber(this);
                    }
                }
            }
        }
    }

    public BufferedImage createImage(){
        BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.paint(image.getGraphics());
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        fontMetrics = graphics2D.getFontMetrics();
        //primenjuje transformaciju ako je ima
        if (transform!=null)
            graphics2D.transform(transform);
        //redosled iscrtavanja - prvo sve veze, pa sve klase, pa lasso, pa obelezavanje selektovanih
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
