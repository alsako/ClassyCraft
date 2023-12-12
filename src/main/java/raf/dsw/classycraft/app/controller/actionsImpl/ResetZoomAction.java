package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ResetZoomAction extends AbstractClassyAction {

    public ResetZoomAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/start.png"));
        putValue(NAME, "Reset zoom");
        putValue(SHORT_DESCRIPTION, "Reset zoom");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            MainFrame.getInstance().getPackageView().getCurrentDiagramView().reset();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
