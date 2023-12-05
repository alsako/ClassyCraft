package raf.dsw.classycraft.app.gui.swing.view.optionframes;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Getter
public class InterclassContentWindow extends JFrame {

    private JButton addMethodButton;
    private JButton addAttributeButton;

    private JLabel contentLabel;
    private JTextArea contentArea;

    private JPanel contentPanel;

    public Boolean addMethod = false;
    public Boolean addAttribute = false;

    public InterclassContentWindow(Interclass interclass) throws HeadlessException {

        addMethod=false;
        addAttribute=false;

        List<String> contents = interclass.getContentStrings();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 4, screenHeight / 3);
        setLocationRelativeTo(null);
        setTitle("Interclass content");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        contentLabel=new JLabel("Class content");

        contentPanel = new JPanel();

        if (!contents.isEmpty()) {
            contentArea = new JTextArea();
            contentArea.setEditable(false);
            contentArea.setPreferredSize(new Dimension(100, 100));
            for (String string:contents) {
                contentArea.append(string + "\n");
            }
            add(new JScrollPane(contentArea), BorderLayout.CENTER);
        }

        addMethodButton = new JButton("Add method");
        addAttributeButton = new JButton("Add attribute");

        addMethodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMethod=true;
                addAttribute=false;
                dispose();
            }
        });

        addAttributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAttribute=true;
                addMethod=false;
                dispose();
            }
        });

        panel.add(contentLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        if (!contents.isEmpty())
            panel.add(contentArea);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(addMethodButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        if (!(interclass instanceof Interfejs))
            panel.add(addAttributeButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));


        getContentPane().add(panel);


    }
}
