package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SetAuthorAction extends AbstractClassyAction {

    public String authorName;

    public SetAuthorAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Author");
        putValue(SHORT_DESCRIPTION, "Set author");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected==null){
            return;
        }
        ClassyNode node = selected.getClassyNode();
        if (node instanceof Project){

            JFrame frame = new JFrame("Enter author name");
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new FlowLayout());

            JTextField textField = new JTextField(20);
            JButton confirmButton = new JButton("Confirm");

            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    authorName = textField.getText();
                    frame.dispose();
                }
            });

            frame.add(textField);
            frame.add(confirmButton);

            frame.setSize(300, 100);
            frame.setVisible(true);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//                    if (authorName.isEmpty())
//                        ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(Event.NAME_CANNOT_BE_EMPTY);
                    ((Project)node).setAuthor(authorName);
//                    if (!(((Project)node).getChildren().isEmpty())){
//                        for (ClassyNode child:((Project) node).getChildren()) {
//                            PackageNotification pn = new PackageNotification(authorName, PackageNtfType.AUTHOR_CHANGED);
//                            ((Package)child).notifySubscribers(pn);
//                        }
//                    }
                    System.out.println( ((Project)node).getName() + " author: " + ((Project)node).getAuthor());
                }
            });

        }
        else {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.AUTHOR_MUST_BE_A_PROJECT);
        }
    }
}
