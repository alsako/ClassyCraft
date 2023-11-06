package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.awt.*;
import java.sql.Struct;
import java.util.List;


@Setter
public class PackageView extends JPanel{

    String packageName;
    String author;
    List<String> diagramNames;

    public PackageView() {
    }

    public void updatePackageView(String packageName, String author, List<String> diagramNames) {

        removeAll();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel pack = new JLabel("package: " + packageName);
        Font customFont1 = new Font("Arial", Font.BOLD, 16);
        pack.setFont(customFont1);
        pack.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel authorLabel = new JLabel("author: " + author);
        Font customFont2 = new Font("Arial", Font.PLAIN, 12);
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
}
