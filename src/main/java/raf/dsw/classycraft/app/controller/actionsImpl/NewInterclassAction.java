package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.optionframes.NewInterclassOption;
import raf.dsw.classycraft.app.messagegen.Event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewInterclassAction extends AbstractClassyAction {

    public static String selectedOption;

    public NewInterclassAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/class.png"));
        putValue(NAME, "New Element");
        putValue(SHORT_DESCRIPTION, "New Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        NewInterclassOption newInterclassOption = new NewInterclassOption();
        newInterclassOption.setVisible(true);
        newInterclassOption.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        newInterclassOption.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                selectedOption = newInterclassOption.getSelectedClassOption();
                System.out.println("Selected Option: " + selectedOption);
                if (selectedOption==null) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.OPTION_NOT_SELECTED);
                }
            }
        });

        MainFrame.getInstance().getPackageView().startAddInterclassState();


    }
}
