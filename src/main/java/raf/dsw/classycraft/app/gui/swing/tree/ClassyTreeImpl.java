package raf.dsw.classycraft.app.gui.swing.tree;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.factory.FactoryUtils;
import raf.dsw.classycraft.app.model.factory.NodeFactory;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.modelImpl.Package;
import raf.dsw.classycraft.app.model.modelImpl.Project;
import raf.dsw.classycraft.app.model.modelImpl.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeImpl implements ClassyTree{

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;


    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {
        if (parent == null) {
            ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(Event.PARENT_NOT_SELECTED);
            return ;
        }
        if(!(parent.getClassyNode() instanceof ClassyNodeComposite)) {
            ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(Event.CANNOT_ADD_CHILD_TO_LEAF);
            return ;
        }
        ClassyNode child = createChild(parent.getClassyNode());
        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    public void removeChild(ClassyTreeItem node){
        if (node==null) {
            ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(Event.NODE_NOT_SELECTED);
            return;
        }
        if (node.getClassyNode() instanceof ProjectExplorer) {
            ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(Event.NODE_CANNOT_BE_DELETED);
            return;
        }
        ClassyNodeComposite parent = (ClassyNodeComposite) node.getClassyNode().getParent();
        parent.removeChild(node.getClassyNode());
        node.removeAllChildren();
        node.removeFromParent();
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

//    private static int projectNum = 1;
    private ClassyNode createChild(ClassyNode parent){
        NodeFactory nodeFactory = FactoryUtils.returnNodeFactory((ClassyNodeComposite) parent);
        return nodeFactory.orderNode((ClassyNodeComposite) parent);
//        if(parent instanceof ProjectExplorer){
//            return new Project(" Project "+ projectNum++, parent);
//        }
//        return null;
    }
}
