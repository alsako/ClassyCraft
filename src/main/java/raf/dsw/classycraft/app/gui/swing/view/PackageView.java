package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.PackageNotification;
import raf.dsw.classycraft.app.model.PackageNtfType;
import raf.dsw.classycraft.app.model.modelImpl.Package;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class PackageView extends JPanel implements ISubscriber {

    Package currentPackage;
    String packageName;
    String author;
    List<String> diagramNames = new ArrayList<>();

    public PackageView() {
    }



    public void showPackageView() {

        removeAll();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel pack = new JLabel("package: " + packageName);
        Font customFont1 = new Font("Arial", Font.BOLD, 12);
        pack.setFont(customFont1);
        pack.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel authorLabel = new JLabel("author: " + author);
        Font customFont2 = new Font("Arial", Font.PLAIN, 10);
        authorLabel.setFont(customFont2);
        authorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane();

        for (String diagram:diagramNames) {
            JComponent panel = new JPanel();
            tabbedPane.addTab(diagram, panel);
        }

        add(pack);
        add(authorLabel);
        add(tabbedPane);

    }

    @Override
    public void update(Object notification) {
        if (notification instanceof PackageNtfType){
            MainFrame.getInstance().getPackageView().removeAll();
            MainFrame.getInstance().revalidate();
            MainFrame.getInstance().repaint();
            return;
        }
        String name = ((PackageNotification)notification).getName();
        switch (((PackageNotification)notification).getType()){
            case RENAME:
                MainFrame.getInstance().getPackageView().setPackageName(name);
                break;
            case ADD_CHILD:
                MainFrame.getInstance().getPackageView().diagramNames.add(name);
                break;
            case AUTHOR_CHANGED:
                MainFrame.getInstance().getPackageView().setAuthor(name);
                break;
            default:
        }
        MainFrame.getInstance().getPackageView().showPackageView();
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
    }
}
