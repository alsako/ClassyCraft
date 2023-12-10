package raf.dsw.classycraft.app.gui.swing.view.optionframes;

import lombok.Getter;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Getter
public class NewContentOption extends JFrame {
    private JRadioButton publicRadioButton;
    private JRadioButton privateRadioButton;
    private JRadioButton protectedRadioButton;

    private JTextField nameInput;
    private JTextField typeInput;

    private JLabel visibilityLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;

    private JButton confirmButton;


    private String enteredName;
    private String enteredType;
    private VisibilityTypes selectedVisibility;

    public NewContentOption(String existingName, String existingType, VisibilityTypes existingVisibility) throws HeadlessException {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 8, screenHeight / 2);
        setLocationRelativeTo(null);
        setTitle("Content setup");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        publicRadioButton = new JRadioButton("public");
        privateRadioButton = new JRadioButton("private");
        protectedRadioButton = new JRadioButton("protected");

        ButtonGroup bg = new ButtonGroup();
        bg.add(publicRadioButton);
        bg.add(privateRadioButton);
        bg.add(protectedRadioButton);

        if (existingVisibility!=null){
            switch (existingVisibility){
                case PRIVATE:
                    privateRadioButton.setSelected(true);
                    break;
                case PROTECTED:
                    protectedRadioButton.setSelected(true);
                    break;
                case PUBLIC:
                    publicRadioButton.setSelected(true);
                    break;
            }
        }

        visibilityLabel = new JLabel("Choose visibility:");
        typeLabel = new JLabel("Enter return type:");
        nameLabel = new JLabel("Enter name:");

        nameInput = new JTextField(20);
        if (existingName!=null)
            nameInput.setText(existingName);
        nameInput.setMaximumSize(new Dimension(nameInput.getPreferredSize().width, nameInput.getPreferredSize().height));
        typeInput = new JTextField(20);
        if (existingType!=null)
            typeInput.setText(existingType);
        typeInput.setMaximumSize(new Dimension(typeInput.getPreferredSize().width, typeInput.getPreferredSize().height));




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
                enteredType = typeInput.getText();
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
        panel.add(typeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(typeInput);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(confirmButton);

        getContentPane().add(panel);


    }
}
