package raf.dsw.classycraft.app.controller.actionsImpl;


import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.NewPackageOption;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.Package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractClassyAction {

   public static String selectedOption;

    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected==null){
            MainFrame.getInstance().getClassyTree().addChild(selected);
            return;
        }
        ClassyNode selectedClassyNode = selected.getClassyNode();
        if (selectedClassyNode instanceof Package){
            NewPackageOption newPackageOption = new NewPackageOption();
            newPackageOption.setVisible(true);
            newPackageOption.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            newPackageOption.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // Here, you can perform actions that depend on the selectedOption
                    selectedOption = newPackageOption.getSelectedOption();
                    System.out.println("Selected Option: " + selectedOption);
                    MainFrame.getInstance().getClassyTree().addChild(selected);
                    return;
                }
            });

        }
        else
            MainFrame.getInstance().getClassyTree().addChild(selected);
    }
}
