package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.commands.ChangeContentCommand;
import raf.dsw.classycraft.app.controller.MakeNewMethod;
import raf.dsw.classycraft.app.controller.MakeNewAttribute;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.optionframes.InterclassContentWindow;
import raf.dsw.classycraft.app.gui.swing.view.painters.*;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messagegen.Event;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ChangeContentState implements ClassyState{

    @Override
    public void misKliknut(Point p, DiagramView diagramView) {

        ChangeContentCommand changeContentCommand = new ChangeContentCommand(diagramView, p);
        diagramView.getCommandManager().addCommand(changeContentCommand);

//        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());
//
//        for (ElementPainter painter:diagramPainters) {
//            if(painter instanceof InterclassPainter && painter.elementAt(p.x, p.y)){
//
//                InterclassContentWindow icw = new InterclassContentWindow((Interclass) painter.getElement());
//                icw.setVisible(true);
//                icw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//
//                icw.addWindowListener(new WindowAdapter() {
//                    @Override
//                    public void windowClosed(WindowEvent windowEvent) {
//                        if (!icw.addMethod && !icw.addAttribute)
//                            return;
//                        if (icw.addAttribute && !icw.addMethod){
//                            MakeNewAttribute mna = new MakeNewAttribute();
//                            mna.trigger(diagramView, painter);
//                        } else if (!icw.addAttribute && icw.addMethod) {
//                            MakeNewMethod mnm = new MakeNewMethod();
//                            mnm.trigger(diagramView, painter);
//                        }
//                    }
//                });
//                return;
//            }
//        }
//        ApplicationFramework.getInstance().getMessageGenerator().generateMessage(Event.CLASS_NOT_SELECTED);

    }

    @Override
    public void misPrevucen(Point p, DiagramView diagramView) {
        //ne treba
    }

    @Override
    public void misOtpusten(Point p, DiagramView diagramView) {
        //ne treba
    }
}
