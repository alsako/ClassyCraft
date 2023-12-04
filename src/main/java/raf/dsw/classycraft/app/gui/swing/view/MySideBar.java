package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;

public class MySideBar extends JToolBar {

    public MySideBar() {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewInterclassAction());
        add(MainFrame.getInstance().getActionManager().getNewConnectionAction());
        add(MainFrame.getInstance().getActionManager().getNewMethodAction());
        add(MainFrame.getInstance().getActionManager().getDeleteElementAction());
    }
}
