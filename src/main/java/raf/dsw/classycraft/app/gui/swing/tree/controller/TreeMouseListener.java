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
                System.out.println("Dvoklik na = " + ((Package)node).getName());

                String packName = node.getName();
                String packAuthor = "Unspecified";

                Project parentProject = ((Package)node).getParentProject();
                if(parentProject.getAuthor()!=null)
                    packAuthor = parentProject.getAuthor();

                List<String> diagramNames = ((Package)node).getDiagramChildrenNames();

                System.out.println("diagramNames = " + diagramNames);
                System.out.println("packName = " + packName);
                MainFrame.getInstance().getPackageView().setCurrentPackage((Package)node);
                MainFrame.getInstance().getPackageView().setPackageName(packName);
                MainFrame.getInstance().getPackageView().setAuthor(packAuthor);
                MainFrame.getInstance().getPackageView().setDiagramNames(diagramNames);
                MainFrame.getInstance().getPackageView().showPackageView();
                MainFrame.getInstance().revalidate();
                MainFrame.getInstance().repaint();
            }
        }
    }

}
