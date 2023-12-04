package raf.dsw.classycraft.app.gui.swing.view.optionframes;

import lombok.Getter;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Getter
public class NewMethodOption extends JFrame {

    private JRadioButton publicRadioButton;
    private JRadioButton privateRadioButton;
    private JRadioButton protectedRadioButton;

    private JTextField nameInput;
    private JTextField returnInput;

    private JLabel visibilityLabel;
    private JLabel nameLabel;
    private JLabel returnLabel;

    private JButton confirmButton;


    private String enteredName;
    private String enteredType;
    private VisibilityTypes selectedVisibility;

    public NewMethodOption() throws HeadlessException {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setTitle("Method setup");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        int padding = 10;


        publicRadioButton = new JRadioButton("public");
        privateRadioButton = new JRadioButton("private");
        protectedRadioButton = new JRadioButton("protected");

        ButtonGroup bg = new ButtonGroup();
        bg.add(publicRadioButton);
        bg.add(privateRadioButton);
        bg.add(protectedRadioButton);

        visibilityLabel = new JLabel("Choose visibility:");
        returnLabel = new JLabel("Enter return type:");
        nameLabel = new JLabel("Enter method name:");

        nameInput = new JTextField(20);
        nameInput.setMaximumSize(new Dimension(Short.MAX_VALUE, nameInput.getPreferredSize().height));
        returnInput = new JTextField(20);
        returnInput.setMaximumSize(new Dimension(Short.MAX_VALUE, returnInput.getPreferredSize().height));




        ActionListener radioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (publicRadioButton.isSelected()) {
                    selectedVisibility = VisibilityTypes.PUBLIC;
                } else if (privateRadioButton.isSelected()) {
                    selectedVisibility = VisibilityTypes.PRIVATE;
                } else if (protectedRadioButton.isSelected()){
                    selectedVisibility = VisibilityTypes.PROTECTED;
                } else selectedVisibility = null;
            }
        };

        publicRadioButton.addActionListener(radioListener);
        privateRadioButton.addActionListener(radioListener);
        protectedRadioButton.addActionListener(radioListener);


        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredName = nameInput.getText();
                enteredType = returnInput.getText();
                dispose();
            }
        });

        panel.add(visibilityLabel);
        panel.add(publicRadioButton);
        panel.add(privateRadioButton);
        panel.add(protectedRadioButton);
        panel.add(nameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(nameInput);
        panel.add(returnLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(returnInput);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(confirmButton);

        getContentPane().add(panel);


    }
}
