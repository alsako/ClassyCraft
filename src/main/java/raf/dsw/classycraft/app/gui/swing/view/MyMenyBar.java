package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getExitAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getNewProjectAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getDeleteNodeAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getOpenProjectAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getLoadDiagramAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getSaveAction()));
        fileMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getExportPhotoAction()));

        for (int i=0; i<fileMenu.getItemCount();i++){
            JMenuItem item = fileMenu.getItem(i);
            item.setIcon(null);
        }

        add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getUndoAction()));
        editMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getRedoAction()));
        editMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getSetAuthorAction()));
        editMenu.add(new JMenuItem(MainFrame.getInstance().getActionManager().getAboutUsAction()));

        for (int i=0; i<editMenu.getItemCount();i++){
            JMenuItem item = editMenu.getItem(i);
            item.setIcon(null);
        }

        add(editMenu);
    }

}
