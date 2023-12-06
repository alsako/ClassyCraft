package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;

public class MySideBar extends JToolBar {

    public MySideBar() {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewInterclassAction());
        add(MainFrame.getInstance().getActionManager().getNewConnectionAction());
        add(MainFrame.getInstance().getActionManager().getChangeContentAction());
        add(MainFrame.getInstance().getActionManager().getDeleteElementAction());
        add(MainFrame.getInstance().getActionManager().getSelectAction());
        add(MainFrame.getInstance().getActionManager().getMoveAction());
        add(MainFrame.getInstance().getActionManager().getDuplicateAction());
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());
    }
}
