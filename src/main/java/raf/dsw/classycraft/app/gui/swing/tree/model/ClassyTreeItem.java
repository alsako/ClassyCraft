package raf.dsw.classycraft.app.gui.swing.tree.model;

import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;


public class ClassyTreeItem extends DefaultMutableTreeNode {

    private ClassyNode classyNode;

    public ClassyTreeItem(ClassyNode nodeModel) {
        this.classyNode = nodeModel;
    }

    @Override
    public String toString() {
        return classyNode.getName();
    }

    public void setName(String name){
        this.classyNode.setName(name);
    }

    public ClassyNode getClassyNode() {
        return classyNode;
    }
}
