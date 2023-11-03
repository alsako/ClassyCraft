package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction {

    public AboutUsAction(){
        putValue(SMALL_ICON, loadIcon("/images/aboutUs.png"));
        putValue(NAME, "AboutUs");
        putValue(SHORT_DESCRIPTION, "About us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame aboutUsFrame = new JFrame("About Us");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        aboutUsFrame.setSize(screenWidth / 3, screenHeight / 3);
        aboutUsFrame.setLocationRelativeTo(null);
        aboutUsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //paneli
        JPanel parentPanel = new JPanel();
        JPanel student1 = new JPanel();
        JPanel student2 = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.X_AXIS));
        student1.setLayout(new BoxLayout(student1, BoxLayout.Y_AXIS));
        student2.setLayout(new BoxLayout(student2, BoxLayout.Y_AXIS));

        //labele i slike
        JLabel studentL1 = new JLabel("Aleksandra Kovacevic");
        studentL1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel studentL2 = new JLabel("Vukasin Kovacevic");
        studentL2.setAlignmentX(Component.CENTER_ALIGNMENT);
        Icon pic1 = loadIcon("/images/student1.png");
        Icon pic2 = loadIcon("/images/student2.png");
        JLabel studentPic1 = new JLabel(pic1);
        studentPic1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel studentPic2 = new JLabel(pic2);
        studentPic2.setAlignmentX(Component.CENTER_ALIGNMENT);

        //dodavanje komponenti
        student1.add(studentL1);
        student1.add(studentPic1);
        student2.add(studentL2);
        student2.add(studentPic2);
        parentPanel.add(Box.createGlue());
        parentPanel.add(student1);
        parentPanel.add(Box.createRigidArea(new Dimension(128,0)));
        parentPanel.add(student2);
        parentPanel.add(Box.createGlue());
        aboutUsFrame.add(parentPanel);
        aboutUsFrame.setVisible(true);
    }
}
