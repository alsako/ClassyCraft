package raf.dsw.classycraft.app.gui.swing.view.optionframes;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Atribut;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.ClassContent;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Metoda;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
public class InterclassContentWindow extends JFrame {

    private JButton addMethodButton;
    private JButton addAttributeButton;
    private JButton deleteButton;
    private JButton editButton;


    private JLabel contentLabel;
    private JList<String> classContentJList;
    private DefaultListModel<String> defaultListModel;

    private JPanel contentPanel;

    public Boolean addMethod = false;
    public Boolean addAttribute = false;

    public InterclassContentWindow(Interclass interclass) throws HeadlessException {

        addMethod=false;
        addAttribute=false;

        defaultListModel = new DefaultListModel<>();
        List<String> contents = interclass.getContentStrings();
        for (String content: contents) {
            defaultListModel.addElement(content);
        }
        classContentJList = new JList<>(defaultListModel);


        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 3, screenHeight / 2);
        setLocationRelativeTo(null);
        setTitle("Interclass content");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentLabel=new JLabel("Class content");


        addMethodButton = new JButton("Add method");
        addAttributeButton = new JButton("Add attribute");
        deleteButton = new JButton("Delete");
        editButton = new JButton("Edit");



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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = classContentJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    interclass.removeContent(selectedIndex);
                    defaultListModel.remove(selectedIndex);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = classContentJList.getSelectedIndex();
                if (selectedIndex!=-1){
                    if (interclass.getClassContentList().get(selectedIndex) instanceof Atribut){
                        Atribut atribut = (Atribut)interclass.getClassContentList().get(selectedIndex);
                        NewContentOption newContentOption = new NewContentOption(atribut.getName(), atribut.getType(),atribut.getVisibility());
                        newContentOption.setVisible(true);
                        newContentOption.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        newContentOption.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent windowEvent) {
                                VisibilityTypes selectedVisibility = newContentOption.getSelectedVisibility();
                                String selectedName = newContentOption.getEnteredName();
                                String selectedType = newContentOption.getEnteredType();
                                if (selectedVisibility!=null)
                                    atribut.setVisibility(selectedVisibility);
                                if (selectedName!=null)
                                    atribut.setName(selectedName);
                                if (selectedType!=null)
                                    atribut.setType(selectedType);
                                defaultListModel.set(selectedIndex,interclass.getClassContentList().get(selectedIndex).toString());

                            }
                        });
                    }
                    else if (interclass.getClassContentList().get(selectedIndex) instanceof Metoda) {
                        Metoda metoda = (Metoda) interclass.getClassContentList().get(selectedIndex);
                        NewContentOption newContentOption = new NewContentOption(metoda.getName(), metoda.getType(),metoda.getVisibility());
                        newContentOption.setVisible(true);
                        newContentOption.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        newContentOption.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent windowEvent) {
                                VisibilityTypes selectedVisibility = newContentOption.getSelectedVisibility();
                                String selectedName = newContentOption.getEnteredName();
                                String selectedType = newContentOption.getEnteredType();
                                if (selectedVisibility!=null)
                                    metoda.setVisibility(selectedVisibility);
                                if (selectedName!=null)
                                    metoda.setName(selectedName);
                                if (selectedType!=null)
                                    metoda.setType(selectedType);
                                defaultListModel.set(selectedIndex,interclass.getClassContentList().get(selectedIndex).toString());

                            }
                        });
                    }

                }
            }
        });

        contentPanel.add(contentLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        if (!contents.isEmpty())
            contentPanel.add(new JScrollPane(classContentJList), BorderLayout.CENTER);
        add(contentPanel, BorderLayout.SOUTH);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(addMethodButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        if (!(interclass instanceof Interfejs))
            contentPanel.add(addAttributeButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(deleteButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(editButton);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));



        getContentPane().add(contentPanel);


    }

}
