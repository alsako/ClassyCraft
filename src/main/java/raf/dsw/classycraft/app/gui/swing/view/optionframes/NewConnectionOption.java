package raf.dsw.classycraft.app.gui.swing.view.optionframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewConnectionOption extends JFrame {
    private JRadioButton agregacijaRadioButton;
    private JRadioButton kompozicijaRadioButton;
    private JRadioButton zavisnostRadioButton;

    private JRadioButton generalizacijaRadioButton;

    private JButton confirmButton;
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


        agregacijaRadioButton = new JRadioButton("Aggregation");
        kompozicijaRadioButton = new JRadioButton("Composition");
        zavisnostRadioButton = new JRadioButton("Dependency");
        generalizacijaRadioButton = new JRadioButton("Generalization");



        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(agregacijaRadioButton);
        buttonGroup.add(kompozicijaRadioButton);
        buttonGroup.add(zavisnostRadioButton);
        buttonGroup.add(generalizacijaRadioButton);

        choiceLabel = new JLabel("Choose the type of connection you want to add: ");

        ActionListener radioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (agregacijaRadioButton.isSelected()) {
                    selectedConnectionOption = "aggregation";
                } else if (kompozicijaRadioButton.isSelected()) {
                    selectedConnectionOption = "composition";
                } else if (zavisnostRadioButton.isSelected()){
                    selectedConnectionOption = "dependency";
                } else if (generalizacijaRadioButton.isSelected()) {
                    selectedConnectionOption = "generalization";
                } else selectedConnectionOption = null;
            }
        };

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        agregacijaRadioButton.addActionListener(radioListener);
        kompozicijaRadioButton.addActionListener(radioListener);
        zavisnostRadioButton.addActionListener(radioListener);
        generalizacijaRadioButton.addActionListener(radioListener);


        add(choiceLabel);
        add(agregacijaRadioButton);
        add(kompozicijaRadioButton);
        add(zavisnostRadioButton);
        add(generalizacijaRadioButton);
        add(confirmButton);

        setSize(300, 150);

    }


    public String getSelectedConnectionOption() {
        return selectedConnectionOption;
    }

}
