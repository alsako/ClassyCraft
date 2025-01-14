package raf.dsw.classycraft.app.commands;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.EnumPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterfejsPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.KlasaPainter;
import raf.dsw.classycraft.app.model.modelImpl.classes.Enum;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interclass;
import raf.dsw.classycraft.app.model.modelImpl.classes.Interfejs;
import raf.dsw.classycraft.app.model.modelImpl.classes.Klasa;
import raf.dsw.classycraft.app.state.DuplicateState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DuplicateCommand extends AbstractCommand{

    private DiagramView diagramView;
    private Point p;
    private List<ElementPainter> diagramPainters = new ArrayList<>();
    private List<ElementPainter> selectedPainters;
    private List<ElementPainter> affectedPainters = new ArrayList<>();

    public DuplicateCommand(DiagramView diagramView, Point p, List<ElementPainter> selectedPainters) {
        this.diagramView = diagramView;
        this.p = p;
        this.selectedPainters = selectedPainters;
    }



    @Override
    public void doCommand() {

        diagramPainters = MainFrame.getInstance().getPackageView().getDiagramPainters().get(diagramView.getDiagram());

        if (!(affectedPainters.isEmpty())){ //ovo je redo za multiselekciju
            for (ElementPainter affectedPainter:affectedPainters) {
                MainFrame.getInstance().getPackageView().addPainterToMap(affectedPainter);
                affectedPainter.getElement().addSubscriber(diagramView);
            }
        }
        else if (selectedPainters==null || !(selectedPainters.isEmpty())){
            for (ElementPainter selectedPainter:selectedPainters) { //prvi do za multiselekciju
                if (selectedPainter instanceof InterclassPainter)
                    affectedPainters.add(duplicate((InterclassPainter) selectedPainter, diagramView));
            }
        }
        else {
            for (int i = diagramPainters.size() - 1; i >= 0; i--) {
                if (diagramPainters.get(i) instanceof InterclassPainter && diagramPainters.get(i).elementAt(p.x, p.y)) {
                    duplicate((InterclassPainter) diagramPainters.get(i), diagramView);
                    return;
                }
            }
        }
    }

    public ElementPainter duplicate(InterclassPainter painter, DiagramView diagramView){
        Interclass duplicate = ((Interclass)painter.getElement()).duplicate();
        duplicate.addSubscriber(diagramView);
        ElementPainter duplicatePainter;
        if (duplicate instanceof Klasa)
            duplicatePainter = new KlasaPainter((Klasa)duplicate);
        else if (duplicate instanceof Interfejs) {
            duplicatePainter = new InterfejsPainter((Interfejs) duplicate);
        }
        else duplicatePainter = new EnumPainter((Enum) duplicate);
        MainFrame.getInstance().getPackageView().addPainterToMap(duplicatePainter);
        return duplicatePainter;
    }

    @Override
    public void undoCommand() {

        if (affectedPainters.isEmpty()){
            MainFrame.getInstance().getPackageView().removePainterFromMap(diagramPainters.get(diagramPainters.size()-1));
            diagramPainters.get(diagramPainters.size()-1).getElement().removeSubscriber(diagramView);
        }
        else { //za multiselekciju
            for (ElementPainter elementPainter: affectedPainters) {
                MainFrame.getInstance().getPackageView().removePainterFromMap(elementPainter);
                diagramPainters.get(diagramPainters.size()-1).getElement().removeSubscriber(diagramView);
            }
        }


    }
}
