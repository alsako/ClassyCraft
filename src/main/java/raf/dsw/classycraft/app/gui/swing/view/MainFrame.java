package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImpl;
import raf.dsw.classycraft.app.messagegen.Message;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;




public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance;

    private ActionManager actionManager;

    private ClassyTree classyTree;

    private MainFrame(){
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    private void initialize(){

        actionManager = new ActionManager();
        classyTree = new ClassyTreeImpl();

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

        JTree projectExplorer = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getRoot());

        JScrollPane scroll = new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200, 150));
        JPanel panel = new JPanel();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(scroll);
        splitPane.setRightComponent(panel);
        getContentPane().add(splitPane);
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);
    }

    public static MainFrame getInstance() {
        if (instance==null) {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    public ClassyTree getClassyTree() {
        return classyTree;
    }

    @Override
    public void update(Object message) {
        JOptionPane.showMessageDialog(MainFrame.getInstance(), ((Message)message).toString());
    }
}
