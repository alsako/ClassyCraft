package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.connections.Agregacija;
import raf.dsw.classycraft.app.model.modelImpl.connections.Zavisnost;

import java.awt.*;
import java.awt.geom.Line2D;

public class ZavisnostPainter extends ConnectionPainter{


    public ZavisnostPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Zavisnost zavisnost = (Zavisnost) (super.getElement());
        Graphics2D g2D = (Graphics2D)g;

        double startX = zavisnost.getFromCoordinates().x + (double)zavisnost.getFromElement().getWidth()/2;
        System.out.println(zavisnost.getFromCoordinates().x);
        System.out.println((double)zavisnost.getFromElement().getWidth()/2);
        double startY = zavisnost.getFromCoordinates().y + (double)zavisnost.getFromElement().getHeight()/2;
        Line2D.Double line = new Line2D.Double(startX, startY, zavisnost.getX(), zavisnost.getY());
        setShape(line);

        float[] dashPattern = {5, 5};
        BasicStroke dashedStroke = new BasicStroke(zavisnost.getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dashPattern, 0);

        g2D.setStroke(dashedStroke);
        g2D.setColor(zavisnost.getColourOutline());

        g2D.draw(getShape());
    }

}
