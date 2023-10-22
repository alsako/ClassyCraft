package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ActionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private ActionManager actionManager;

    private MainFrame(){
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    private void initialize(){

        actionManager = new ActionManager();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenyBar menu = new MyMenyBar();
        setJMenuBar(menu);

        MyToolBar myToolBar = new MyToolBar();
        add(myToolBar, BorderLayout.NORTH);
    }

    public static MainFrame getInstance() {
        if (instance==null) {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }
}
