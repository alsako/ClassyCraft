package raf.dsw.classycraft.app.gui.swing.tree;

import lombok.Getter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
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
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Enumeration;
import java.util.List;

@Getter
public class ClassyTreeImpl implements ClassyTree{

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;

    ClassyTreeItem root;


    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {
        if (parent == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.PARENT_NOT_SELECTED);
            return ;
        }
        if(!(parent.getClassyNode() instanceof ClassyNodeComposite)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.CANNOT_ADD_CHILD_TO_LEAF);
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
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.NODE_NOT_SELECTED);
            return;
        }
        if (node.getClassyNode() instanceof ProjectExplorer) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.NODE_CANNOT_BE_DELETED);
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

    private ClassyNode createChild(ClassyNode parent){
        NodeFactory nodeFactory = FactoryUtils.returnNodeFactory((ClassyNodeComposite) parent);
        return nodeFactory.orderNode((ClassyNodeComposite) parent);
    }

    public TreePath findPathToNode(ClassyTreeItem root, ClassyNode current) {
        Enumeration<TreeNode> element = root.depthFirstEnumeration();
        while (element.hasMoreElements()) {
            ClassyTreeItem node = (ClassyTreeItem) element.nextElement();
            if (node.getClassyNode().equals(current)) {
                return new TreePath(node.getPath());
            }
        }
        return null;
    }
    public void addToTree(ClassyNode parent, ClassyNode child){
        TreePath pathToDiagram = ((ClassyTreeImpl)MainFrame.getInstance().getClassyTree()).findPathToNode(root, parent);
        ClassyTree tree = MainFrame.getInstance().getClassyTree();
        ((ClassyTreeImpl)tree).getTreeView().setSelectionPath(pathToDiagram);
        ClassyTreeItem parentItem = tree.getSelectedNode();
        parentItem.add(new ClassyTreeItem(child));
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    public void removeFromTree(ClassyNode child){
        TreePath pathToChild = ((ClassyTreeImpl)MainFrame.getInstance().getClassyTree()).findPathToNode(root, child);
        ClassyTree tree = MainFrame.getInstance().getClassyTree();
        ((ClassyTreeImpl)tree).getTreeView().setSelectionPath(pathToChild);
        ClassyTreeItem childItem = tree.getSelectedNode();
        tree.removeChild(childItem);
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void loadProject(Project node) {
        ClassyTreeItem loadProject = new ClassyTreeItem(node);
        root.add(loadProject);
        addChildrenToTree(loadProject);
        ((ClassyNodeComposite) root.getClassyNode()).addChild(node);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    public void addChildrenToTree(ClassyTreeItem node){
        List<ClassyNode> children = ((ClassyNodeComposite)node.getClassyNode()).getChildren();
        for (ClassyNode child:children) {
            ClassyTreeItem newItem = new ClassyTreeItem(child);
            node.add(newItem);
            if (child instanceof ClassyNodeComposite)
                addChildrenToTree(newItem);
        }
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }


}
