package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.controller.DiagramViewMouseListener;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.painters.InterclassPainter;
import raf.dsw.classycraft.app.model.notifications.DiagramNtfType;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;

    private List<ElementPainter> painters = new ArrayList<>();

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
//                System.out.println("index = " + index);
                if (index>=0)
                    MainFrame.getInstance().getPackageView().getTabbedPane().setTitleAt(index, this.diagram.getName());
                break;
            case REPAINT:
                this.repaint();
        }
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        for (ElementPainter painter:painters) {
            if (painter instanceof ConnectionPainter)
                     painter.draw(graphics2D);
        }
        for (ElementPainter painter:painters) {
            if (painter instanceof InterclassPainter)
                painter.draw(graphics2D);
        }
    }
}
