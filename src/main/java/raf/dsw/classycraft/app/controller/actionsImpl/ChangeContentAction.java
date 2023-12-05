package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeContentAction extends AbstractClassyAction {

    public ChangeContentAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/change.png"));
        putValue(NAME, "Alter interclass content");
        putValue(SHORT_DESCRIPTION, "Alter interclass content");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startChangeContentState();
    }
}
