package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPackageOption extends JFrame {

    private JRadioButton packageRadioButton;
    private JRadioButton diagramRadioButton;
    private JButton confirmButton;
    private JLabel choiceLabel;

    private String selectedOption;

    public NewPackageOption() throws HeadlessException {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 3, screenHeight / 3);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Child choice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        packageRadioButton = new JRadioButton("Package");
        diagramRadioButton = new JRadioButton("Diagram");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(packageRadioButton);
        buttonGroup.add(diagramRadioButton);

        choiceLabel = new JLabel("Choose the type of element you want to add: ");

        ActionListener radioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (packageRadioButton.isSelected()) {
                    selectedOption = "package";
                } else if (diagramRadioButton.isSelected()) {
                    selectedOption = "diagram";
                }
                else selectedOption = null;
            }
        };

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        packageRadioButton.addActionListener(radioListener);
        diagramRadioButton.addActionListener(radioListener);


        add(choiceLabel);
        add(packageRadioButton);
        add(diagramRadioButton);
        add(confirmButton);

        setSize(300, 150);

    }


    public String getSelectedOption() {
        return selectedOption;
    }
}
