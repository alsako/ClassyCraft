package raf.dsw.classycraft.app.gui.swing.view.optionframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewInterclassOption extends JFrame{

    private JButton klasaButton;
    private JButton interfejsButton;
    private JButton enumButton;
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


        choiceLabel = new JLabel("Choose the type of element you want to add: ");


        klasaButton = new JButton("Class");
        klasaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassOption = "class";
                dispose();
            }
        });
        interfejsButton = new JButton("Interface");
        interfejsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassOption = "interface";
                dispose();
            }
        });
        enumButton = new JButton("Enum");
        enumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassOption = "enum";
                dispose();
            }
        });



        add(choiceLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(klasaButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(interfejsButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(enumButton);

        setSize(300, 150);

    }


    public String getSelectedClassOption() {
        return selectedClassOption;
    }

}
