package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.optionframes.NewMethodOption;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.Metoda;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MakeNewMethod {

    public static VisibilityTypes selectedVisibility;
    public static String selectedName;
    public static String selectedType;


    public void trigger(DiagramView diagramView, ElementPainter painter){
        NewMethodOption nmo = new NewMethodOption();
        nmo.setVisible(true);
        nmo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        nmo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                selectedVisibility = nmo.getSelectedVisibility();
                selectedName = nmo.getEnteredName();
                selectedType = nmo.getEnteredType();
                if (selectedVisibility==null || selectedName==null || selectedType==null){
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.OPTION_NOT_SELECTED);
                    return;
                }
                Metoda metoda = new Metoda(selectedName, selectedVisibility, selectedType);
                metoda.setInterclass((Interclass) painter.getElement());
                ((Interclass)painter.getElement()).getClassContentList().add(metoda);
                diagramView.repaint();
                return;
            }
        });
        return;
    }

}
