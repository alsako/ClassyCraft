package raf.dsw.classycraft.app.gui.swing.tree.controller;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.Package;
import raf.dsw.classycraft.app.model.modelImpl.Project;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TreeMouseListener extends MouseAdapter {

    public TreeMouseListener() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
            ClassyNode node = selected.getClassyNode();
            if (node instanceof Package) {
//                System.out.println("Dvoklik na = " + ((Package)node).getName());
                MainFrame.getInstance().getPackageView().updatePackageView((Package) node);
                MainFrame.getInstance().revalidate();
                MainFrame.getInstance().repaint();
            }
        }
    }

}
