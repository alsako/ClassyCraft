package raf.dsw.classycraft.app.gui.swing.view.painters.interclasses;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.classes.Enum;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.Klasa;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public abstract class InterclassPainter extends ElementPainter {

    public InterclassPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {

        Interclass interclass = (Interclass) super.getElement();

        char type;
        if (interclass instanceof Klasa)
            type = 'C';
        else if (interclass instanceof Enum) {
            type = 'E';
        } else if (interclass instanceof Interfejs) {
            type = 'I';
        } else return;


        List<String> contents = interclass.getContentStrings();


        Graphics2D g2D = (Graphics2D)g;
        FontMetrics fontMetrics = DiagramView.fontMetrics;
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        double startX = interclass.getX();
        double startY = interclass.getY();



        // w i h okvira
        double width;
        double height;
        width = interclass.getWidth();
        height = interclass.getHeight();



        //crtanje
        this.setShape(new Rectangle2D.Double(startX, startY-10, width, height));
        g2D.setStroke(new BasicStroke(interclass.getStrokeWidth()));
        g2D.setColor(interclass.getColourInside());
        g2D.fill(getShape());
        g2D.setColor(interclass.getColourOutline());
        g2D.draw(getShape());

        drawStringCentered(g2D, "[" + type + "]", startX, startY + 2, (int) width,fontMetrics);
        drawStringCentered(g2D, interclass.getName(), startX, startY+ fontMetrics.getHeight(), (int) width,fontMetrics);

        g2D.drawLine((int) startX, (int) (startY+5+fontMetrics.getHeight()), (int) (startX+width), (int) (startY+5+fontMetrics.getHeight()));

        // metode i atributi
        for (int i = 0; i < contents.size(); i++) {
            drawStringCentered(g2D, contents.get(i), startX, startY + 2 + (fontMetrics.getHeight()) * (i+2), (int)width, fontMetrics);
        }

    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }

    public void drawStringCentered(Graphics2D g2d, String text, double x, double y, int width, FontMetrics fontMetrics) {
        g2d.drawString(text, (float) x + (width-fontMetrics.stringWidth(text)) / 2, (float) y);
    }


}
