package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.connections.Agregacija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Generalizacija;

import java.awt.*;
import java.awt.geom.Line2D;

public class GeneralizacijaPainter extends ConnectionPainter{

    public GeneralizacijaPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Generalizacija generalizacija = (Generalizacija) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;
        double startX = generalizacija.getFromCoordinates().x + generalizacija.getFromElement().getWidth()/2;
        double startY = generalizacija.getFromCoordinates().y + generalizacija.getFromElement().getHeight()/2;
        Line2D.Double line = new Line2D.Double(startX, startY, generalizacija.getX(), generalizacija.getY());
        setShape(line);

        g2D.setStroke(new BasicStroke(generalizacija.getStrokeWidth()));
        g2D.setColor(generalizacija.getColourOutline());

        g2D.draw(getShape());
    }

}
