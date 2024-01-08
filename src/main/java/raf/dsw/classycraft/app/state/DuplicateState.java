package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.commands.DuplicateCommand;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.EnumPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterfejsPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.KlasaPainter;
import raf.dsw.classycraft.app.model.modelImpl.DiagramElement;
import raf.dsw.classycraft.app.model.modelImpl.classes.Enum;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.Klasa;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class DuplicateState implements ClassyState{
    @Override
    public void misKliknut(Point p, DiagramView diagramView) {

        DuplicateCommand duplicateCommand = new DuplicateCommand(diagramView, p, diagramView.getSelectedPainters());
        diagramView.getCommandManager().addCommand(duplicateCommand);
//        List<ElementPainter> diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());
//
//            for (int i=diagramPainters.size()-1; i>=0; i--){
//                if (diagramPainters.get(i) instanceof InterclassPainter && diagramPainters.get(i).elementAt(p.x, p.y)){
//                    duplicate((InterclassPainter) diagramPainters.get(i), diagramView);
//                    return;
//                }
//            }

    }

    @Override
    public void misPrevucen(Point p, DiagramView diagramView) {
        //
    }

    @Override
    public void misOtpusten(Point p, DiagramView diagramView) {
        //
    }
}
