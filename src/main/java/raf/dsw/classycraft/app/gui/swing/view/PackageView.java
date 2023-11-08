package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.PackageNotification;
import raf.dsw.classycraft.app.model.PackageNtfType;
import raf.dsw.classycraft.app.model.modelAbs.ClassyNode;
import raf.dsw.classycraft.app.model.modelImpl.Diagram;
import raf.dsw.classycraft.app.model.modelImpl.Package;
import raf.dsw.classycraft.app.model.modelImpl.Project;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class PackageView extends JPanel implements ISubscriber {

    private String packageName = "";
    private String author = "";
    private Package pack;
    private List<DiagramView> tabs = new ArrayList<>();
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JLabel packLabel = new JLabel();
    private JLabel authorLabel = new JLabel();

    public PackageView() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Font customFont1 = new Font("Arial", Font.BOLD, 12);
        packLabel.setFont(customFont1);
        packLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Font customFont2 = new Font("Arial", Font.PLAIN, 10);
        authorLabel.setFont(customFont2);
        authorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(packLabel);
        add(authorLabel);
        add(tabbedPane);
    }



    public void updatePackageView(Package newPackage) {

        if (this.pack != null)
            this.pack.removeSubscriber(this);
        newPackage.addSubscriber(this);

        tabbedPane.removeAll();
        tabs.clear();

        this.setPack(newPackage);
        this.setAuthor(newPackage.getAuthor());
        this.setPackageName(newPackage.getName());
        this.packLabel.setText("package: " + this.packageName);
        if (this.author!=null)
            this.authorLabel.setText("author: " + this.author);


        if (this.pack!=null){
            for (ClassyNode child:this.pack.getChildren()) {
                if (child instanceof Diagram){
                    DiagramView tab = new DiagramView((Diagram)child);
                    ((Diagram)child).addSubscriber(tab);
                    tabs.add(tab);
                }
            }
        }

        for (DiagramView tab:this.tabs) {
            tabbedPane.addTab(tab.getDiagram().getName(), tab);
        }


    }

    @Override
    public void update(Object notification) {
        if (notification instanceof PackageNtfType){
            this.pack = null;
            this.setPackageName("");
            this.packLabel.setText("");
            this.setAuthor("");
            this.authorLabel.setText("");
            this.tabbedPane.removeAll();
            this.tabs.clear();
            return;
        }
        String name = ((PackageNotification)notification).getName();
        switch (((PackageNotification)notification).getType()){
            case RENAME:
                this.setPackageName(name);
                this.packLabel.setText("package: " + name);
                break;
            case ADD_CHILD:
                Diagram addedDiagram = this.pack.getLastDiagramChild();
                DiagramView newTab = new DiagramView(addedDiagram);
                addedDiagram.addSubscriber(newTab);
                tabs.add(newTab);
                tabbedPane.addTab(newTab.getDiagram().getName(), newTab);
                break;
            case AUTHOR_CHANGED:
                this.setAuthor(name);
                this.authorLabel.setText("author: " + name);
                break;
            default:
        }
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
    }
}
