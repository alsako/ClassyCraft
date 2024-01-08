package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.commands.DuplicateCommand;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.interclasses.InterclassPainter;
import raf.dsw.classycraft.app.state.DuplicateState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

public class DuplicateAction extends AbstractClassyAction {
    public DuplicateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/duplicate.png"));
        putValue(NAME, "Duplicate");
        putValue(SHORT_DESCRIPTION, "Duplicate");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<ElementPainter> selectedList = MainFrame.getInstance().getPackageView().getCurrentDiagramView().getSelectedPainters();
        MainFrame.getInstance().getPackageView().startDuplicateState();

//        if (selectedList.isEmpty())
//            MainFrame.getInstance().getPackageView().startDuplicateState();
//        else {
//            DiagramView currentDiagramView = MainFrame.getInstance().getPackageView().getCurrentDiagramView();
//            for (ElementPainter selectedPainter:selectedList) {
//                if (selectedPainter instanceof InterclassPainter)
//                    DuplicateState.duplicate((InterclassPainter) selectedPainter, currentDiagramView);
//            }

        //ako ima selektovanih zelim akciju na klik dugmeta, ne cekam misKliknut
        if (!(selectedList.isEmpty())){
            DiagramView diagramView = MainFrame.getInstance().getPackageView().getCurrentDiagramView();
            DuplicateCommand duplicateCommand = new DuplicateCommand(diagramView, null, diagramView.getSelectedPainters());
            diagramView.getCommandManager().addCommand(duplicateCommand);
        }
    }
}
