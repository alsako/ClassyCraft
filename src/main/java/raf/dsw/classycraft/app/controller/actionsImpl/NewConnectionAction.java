package raf.dsw.classycraft.app.controller.actionsImpl;

import lombok.Getter;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.optionframes.NewConnectionOption;
import raf.dsw.classycraft.app.messagegen.Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewConnectionAction extends AbstractClassyAction {

    public static String selectedOption;

    public NewConnectionAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/connection.png"));
        putValue(NAME, "New Connection");
        putValue(SHORT_DESCRIPTION, "New Connection");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        NewConnectionOption newConnectionOption = new NewConnectionOption();
        newConnectionOption.setVisible(true);
        newConnectionOption.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        newConnectionOption.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                selectedOption = newConnectionOption.getSelectedConnectionOption();
                System.out.println("Selected Option: " + selectedOption);
                if (selectedOption==null){
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.OPTION_NOT_SELECTED);
                    return;
                  }
            }
        });


        MainFrame.getInstance().getPackageView().startAddConnectionState();



    }
}
