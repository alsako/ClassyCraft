package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.optionframes.NewAttributeOption;
import raf.dsw.classycraft.app.gui.swing.view.optionframes.NewMethodOption;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelImpl.classes.Atribut;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.Metoda;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MakeNewAttribute {

    public static VisibilityTypes selectedVisibility;
    public static String selectedName;
    public static String selectedType;

    public void trigger(DiagramView diagramView, ElementPainter painter){

        NewAttributeOption nao = new NewAttributeOption();
        nao.setVisible(true);
        nao.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        nao.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                selectedVisibility = nao.getSelectedVisibility();
                selectedName = nao.getEnteredName();
                selectedType = nao.getEnteredType();
                if (selectedVisibility==null || selectedName==null || selectedType==null){
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.OPTION_NOT_SELECTED);
                    return;
                }
                Atribut atribut = new Atribut(selectedName, selectedVisibility, selectedType);
                atribut.setInterclass((Interclass) painter.getElement());
                ((Interclass)painter.getElement()).getClassContentList().add(atribut);
                diagramView.repaint();
                return;
            }
        });
        return;
    }

}
