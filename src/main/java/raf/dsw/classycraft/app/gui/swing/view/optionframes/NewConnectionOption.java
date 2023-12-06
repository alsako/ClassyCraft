package raf.dsw.classycraft.app.gui.swing.view.optionframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewConnectionOption extends JFrame {

    private JButton agregacijaButton;
    private JButton kompozicijaButton;
    private JButton zavisnostButton;

    private JButton generalizacijaButton;

    private JLabel choiceLabel;

    private String selectedConnectionOption;

    public NewConnectionOption() throws HeadlessException {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Connection choice");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        choiceLabel = new JLabel("Choose the type of connection you want to add: ");

        agregacijaButton = new JButton("Aggregation");
        agregacijaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedConnectionOption = "aggregation";
                dispose();
            }
        });

        kompozicijaButton = new JButton("Composition");
        kompozicijaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedConnectionOption = "composition";
                dispose();
            }
        });

        zavisnostButton = new JButton("Dependency");
        zavisnostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedConnectionOption = "dependency";
                dispose();
            }
        });

        generalizacijaButton = new JButton("Generalization");
        generalizacijaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedConnectionOption = "generalization";
                dispose();
            }
        });


        add(choiceLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(agregacijaButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(kompozicijaButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(zavisnostButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(generalizacijaButton);
        add(Box.createRigidArea(new Dimension(0, 10)));

        setSize(300, 150);

    }

    public String getSelectedConnectionOption() {
        return selectedConnectionOption;
    }

}
