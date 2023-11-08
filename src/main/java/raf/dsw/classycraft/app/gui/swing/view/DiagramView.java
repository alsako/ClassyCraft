package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.DiagramNtfType;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;

    public DiagramView(Diagram diagram) {
        this.diagram = diagram;
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
                MainFrame.getInstance().getPackageView().getTabbedPane().setTitleAt(index, this.diagram.getName());
                break;
        }
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
    }
}
