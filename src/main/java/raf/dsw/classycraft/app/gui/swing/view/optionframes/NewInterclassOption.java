package raf.dsw.classycraft.app.gui.swing.view.optionframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewInterclassOption extends JFrame{

    private JRadioButton klasaRadioButton;
    private JRadioButton interfejsRadioButton;
    private JRadioButton enumRadioButton;
    private JButton confirmButton;
    private JLabel choiceLabel;

    private String selectedClassOption;

    public NewInterclassOption() throws HeadlessException {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 3, screenHeight / 3);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setTitle("Interclass choice");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        klasaRadioButton = new JRadioButton("Class");
        interfejsRadioButton = new JRadioButton("Interface");
        enumRadioButton = new JRadioButton("Enum");


        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(klasaRadioButton);
        buttonGroup.add(interfejsRadioButton);
        buttonGroup.add(enumRadioButton);

        choiceLabel = new JLabel("Choose the type of element you want to add: ");

        ActionListener radioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (klasaRadioButton.isSelected()) {
                    selectedClassOption = "class";
                } else if (interfejsRadioButton.isSelected()) {
                    selectedClassOption = "interface";
                } else if (enumRadioButton.isSelected()){
                    selectedClassOption = "enum";
                }
                else selectedClassOption = null;
            }
        };

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        klasaRadioButton.addActionListener(radioListener);
        interfejsRadioButton.addActionListener(radioListener);
        enumRadioButton.addActionListener(radioListener);


        add(choiceLabel);
        add(klasaRadioButton);
        add(interfejsRadioButton);
        add(enumRadioButton);
        add(confirmButton);

        setSize(300, 150);

    }


    public String getSelectedClassOption() {
        return selectedClassOption;
    }

}
