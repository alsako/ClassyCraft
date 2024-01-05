package raf.dsw.classycraft.app.gui.swing.view.optionframes;

import lombok.Getter;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Atribut;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.Metoda;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

@Getter
public class InterclassContentWindow extends JFrame {

    private JButton addMethodButton;
    private JButton addAttributeButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton changeNameButton;


    private JLabel contentLabel;
    private JList<String> classContentJList;
    private DefaultListModel<String> defaultListModel;
    private JPanel contentPanel;

    public String oldName;
    public VisibilityTypes oldVisibility;
    public String oldType;
    public String newName;
    public VisibilityTypes newVisibility;
    public String newType;
    public int editedIndex = -1;
    public Boolean addMethod = false;
    public Boolean addAttribute = false;
    public Boolean edited = false;
    public Boolean nameEdited = false;
    public String oldClassName;
    public String newClassName;

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
        setSize(screenWidth / 3, screenHeight / 3);
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
        changeNameButton = new JButton("Change entity name");



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

                edited = true;
                int selectedIndex = classContentJList.getSelectedIndex();
                editedIndex = selectedIndex;
                if (selectedIndex!=-1){
                    if (interclass.getClassContentList().get(selectedIndex) instanceof Atribut){
                        Atribut atribut = (Atribut)interclass.getClassContentList().get(selectedIndex);
                        NewContentOption newContentOption = new NewContentOption(atribut.getName(), atribut.getType(),atribut.getVisibility());
                        newContentOption.setVisible(true);
                        newContentOption.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        oldName = atribut.getName();
                        oldVisibility = atribut.getVisibility();
                        oldType = atribut.getType();
                        newName = oldName;
                        newType = oldType;
                        newVisibility = oldVisibility;
                        newContentOption.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent windowEvent) {
                                VisibilityTypes selectedVisibility = newContentOption.getSelectedVisibility();
                                String selectedName = newContentOption.getEnteredName();
                                String selectedType = newContentOption.getEnteredType();
                                if (selectedVisibility!=null) {
                                    atribut.setVisibility(selectedVisibility);
                                    newVisibility = selectedVisibility;
                                }
                                if (selectedName!=null) {
                                    atribut.setName(selectedName);
                                    newName = selectedName;
                                }
                                if (selectedType!=null) {
                                    atribut.setType(selectedType);
                                    newType = selectedType;
                                }
                                interclass.resize();
                                defaultListModel.set(selectedIndex,interclass.getClassContentList().get(selectedIndex).toString());

                            }
                        });
                    }
                    else if (interclass.getClassContentList().get(selectedIndex) instanceof Metoda) {
                        Metoda metoda = (Metoda) interclass.getClassContentList().get(selectedIndex);
                        NewContentOption newContentOption = new NewContentOption(metoda.getName(), metoda.getType(),metoda.getVisibility());
                        newContentOption.setVisible(true);
                        newContentOption.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        oldName = metoda.getName();
                        oldVisibility = metoda.getVisibility();
                        oldType = metoda.getType();
                        newName = oldName;
                        newVisibility = oldVisibility;
                        newType = oldType;
                        newContentOption.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent windowEvent) {
                                VisibilityTypes selectedVisibility = newContentOption.getSelectedVisibility();
                                String selectedName = newContentOption.getEnteredName();
                                String selectedType = newContentOption.getEnteredType();
                                if (selectedVisibility!=null) {
                                    metoda.setVisibility(selectedVisibility);
                                    newVisibility = selectedVisibility;
                                }
                                if (selectedName!=null) {
                                    metoda.setName(selectedName);
                                    newName = selectedName;
                                }
                                if (selectedType!=null) {
                                    metoda.setType(selectedType);
                                    newType = selectedType;
                                }
                                interclass.resize();
                                defaultListModel.set(selectedIndex,interclass.getClassContentList().get(selectedIndex).toString());

                            }
                        });
                    }

                }
            }
        });

        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameEdited = true;
                ChangeNameOption changeNameOption = new ChangeNameOption(interclass.getName());
                changeNameOption.setVisible(true);
                changeNameOption.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                oldClassName = interclass.getName();
                newClassName = oldClassName;
                changeNameOption.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent windowEvent) {
                        String newName = changeNameOption.getNewName();
                        if(newName!= null) {
                            interclass.setName(newName);
                            newClassName = newName;
                        }
                    }
                });
            }
        });


        contentPanel.add(contentLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        if (!contents.isEmpty())
            contentPanel.add(new JScrollPane(classContentJList), BorderLayout.CENTER);
        add(contentPanel, BorderLayout.SOUTH);
        contentPanel.add(changeNameButton);
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
