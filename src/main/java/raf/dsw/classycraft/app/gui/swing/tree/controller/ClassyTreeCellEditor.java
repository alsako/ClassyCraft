package raf.dsw.classycraft.app.gui.swing.tree.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.DiagramNtfType;
import raf.dsw.classycraft.app.model.PackageNotification;
import raf.dsw.classycraft.app.model.PackageNtfType;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Package;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener{

    public Object clickedOn = null;
    public JTextField edit = null;

    public ClassyTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        clickedOn = value;
        edit = new JTextField(value.toString());
        edit.addActionListener(this);
        return edit;
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if(event instanceof MouseEvent){
            if(((MouseEvent) event).getClickCount() == 3){
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(clickedOn instanceof ClassyTreeItem)){
            return ;
        }
        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
//        ClassyNode clickedNode = ((ClassyTreeItem) clickedOn).getClassyNode();

//        if (e.getActionCommand().isEmpty()) {
//            ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(Event.NAME_CANNOT_BE_EMPTY);
//            return;
//        }
//        else if(((ClassyNodeComposite)(clickedNode.getParent())).childNameTaken(e.getActionCommand())){
//                ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(Event.NAME_TAKEN);
//                return;
//        }


        clicked.setName(e.getActionCommand());
        //observer
//        if (clickedNode instanceof Package) {
//            PackageNotification pn = new PackageNotification(e.getActionCommand(), PackageNtfType.RENAME);
//            ((Package)clickedNode).notifySubscribers(pn);
//        }
//        if (clickedNode instanceof Diagram) {
//            ((Diagram) clickedNode).notifySubscribers(DiagramNtfType.RENAME);
//        }


    }
}

