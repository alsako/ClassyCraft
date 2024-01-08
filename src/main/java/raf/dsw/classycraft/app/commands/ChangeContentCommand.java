package raf.dsw.classycraft.app.commands;

import raf.dsw.classycraft.app.controller.MakeNewAttribute;
import raf.dsw.classycraft.app.controller.MakeNewMethod;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.optionframes.InterclassContentWindow;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelImpl.classcontent.ClassContent;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.VisibilityTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

public class ChangeContentCommand extends AbstractCommand{

    private List<ElementPainter> diagramPainters;

    private ElementPainter currPainter;

    private DiagramView diagramView;
    private Point p;
    private boolean edited = false;
    private boolean nameEdited = false;
    private int editedIndex;
    private String oldName;
    private VisibilityTypes oldVisibility;
    private String oldType;
    private String oldClassName;
    private String newClassName;
    private String newName;
    private VisibilityTypes newVisibility;
    private String newType;


    private LinkedList<ClassContent> removed = new LinkedList<>();

    public ChangeContentCommand(DiagramView diagramView, Point p) {
        this.diagramView = diagramView;
        this.p = p;
    }

    @Override
    public void doCommand() {

        diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());
        if(currPainter == null) {
            for (ElementPainter painter : diagramPainters) {
                if (painter instanceof InterclassPainter && painter.elementAt(p.x, p.y)) {


                    currPainter = painter;
                    Interclass element = (Interclass) painter.getElement();
                    element.changed();
                    InterclassContentWindow icw = new InterclassContentWindow(element);
                    icw.setVisible(true);
                    icw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    icw.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent windowEvent) {
                            if (!icw.addMethod && !icw.addAttribute && !icw.edited && !icw.nameEdited)
                                return;
                            if (icw.addAttribute && !icw.addMethod && !icw.edited && !icw.nameEdited) {
                                MakeNewAttribute mna = new MakeNewAttribute();
                                mna.trigger(diagramView, painter);
                            } else if (!icw.addAttribute && icw.addMethod && !icw.edited && !icw.nameEdited) {
                                MakeNewMethod mnm = new MakeNewMethod();
                                mnm.trigger(diagramView, painter);
                            }else if(icw.edited && !icw.nameEdited){
                                edited = true;
                                newName = icw.newName;
                                oldName = icw.oldName;
                                oldVisibility = icw.oldVisibility;
                                newVisibility = icw.newVisibility;
                                oldType = icw.oldType;
                                newType = icw.newType;
                                editedIndex = icw.editedIndex;
                            }else if(icw.nameEdited){
                                nameEdited = true;
                                newClassName = icw.newClassName;
                                oldClassName = icw.oldClassName;
                            }
                        }
                    });
                    return;
                }
            }
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.CLASS_NOT_SELECTED);
        }else{
            int index = diagramPainters.indexOf(currPainter);
            Interclass element = (Interclass) diagramPainters.get(index).getElement();
            element.changed();
            if(edited){
                ClassContent editovan = element.getClassContentList().get(editedIndex);
                editovan.setName(newName);
                editovan.setVisibility(newVisibility);
                editovan.setContentType(newType);
                element.resize();
            }else if(nameEdited) {
                element.setName(newClassName);
            }else{
                element.addContent(removed.get(0));
            }
        }
    }

    @Override
    public void undoCommand() {
        System.out.println(nameEdited);
        int index = diagramPainters.indexOf(currPainter);
        Interclass element = (Interclass) diagramPainters.get(index).getElement();

        int pointer = element.getAdditionOrder().size()-1;

        if(edited && !nameEdited){
           ClassContent editovan = element.getClassContentList().get(editedIndex);
           editovan.setName(oldName);
           editovan.setVisibility(oldVisibility);
           editovan.setContentType(oldType);
           element.resize();
        }else if(nameEdited){
            element.setName(oldClassName);
        }else if (!element.getClassContentList().isEmpty() && currPainter != null) {
            //brise poslednji dodat el
            element.removeContent(element.getClassContentList().indexOf(element.getAdditionOrder().get(pointer)));
            //dodaje se u listu izbrisanih
            removed.add(element.getAdditionOrder().get(pointer));
            element.getAdditionOrder().remove(pointer);
        }




    }
}
