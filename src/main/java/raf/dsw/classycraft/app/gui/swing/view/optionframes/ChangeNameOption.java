package raf.dsw.classycraft.app.gui.swing.view.optionframes;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public class ChangeNameOption extends JFrame{

    private JLabel changeNameLabel;
    private JTextField newNameInput;

    private JButton confirmButton;

    private String newName;

    public ChangeNameOption(String existingName){
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

        changeNameLabel = new JLabel("Change entity name:");

        newNameInput = new JTextField(20);
        if (existingName!=null)
            newNameInput.setText(existingName);
        newNameInput.setMaximumSize(new Dimension(newNameInput.getPreferredSize().width, newNameInput.getPreferredSize().height));

        confirmButton = new JButton("Confirm");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newName = newNameInput.getText();
                dispose();
            }
        });

        panel.add(changeNameLabel);
        panel.add(newNameInput);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(confirmButton);

        getContentPane().add(panel);

    }

}

