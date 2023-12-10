package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.optionframes.NewContentOption;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Metoda;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MakeNewMethod {

    public static VisibilityTypes selectedVisibility;
    public static String selectedName;
    public static String selectedType;


    public void trigger(DiagramView diagramView, ElementPainter painter){
        NewContentOption newContentOption = new NewContentOption(null, null, null);
        newContentOption.setVisible(true);
        newContentOption.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        newContentOption.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                selectedVisibility = newContentOption.getSelectedVisibility();
                selectedName = newContentOption.getEnteredName();
                selectedType = newContentOption.getEnteredType();
                if (selectedVisibility==null || selectedName==null || selectedType==null){
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.OPTION_NOT_SELECTED);
                    return;
                }
                Metoda metoda = new Metoda(selectedName, selectedVisibility, selectedType);
                metoda.addSubscriber(diagramView);
                ((Interclass)painter.getElement()).addContent(metoda);
            }
        });
    }

}
