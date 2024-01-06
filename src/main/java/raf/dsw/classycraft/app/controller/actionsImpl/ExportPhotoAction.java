package raf.dsw.classycraft.app.controller.actionsImpl;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportPhotoAction extends AbstractClassyAction {

    public ExportPhotoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/capture.png"));
        putValue(NAME, "Export as photo");
        putValue(SHORT_DESCRIPTION, "Export as photo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PackageView packageView = MainFrame.getInstance().getPackageView();
        DiagramView currentDiagramView = (DiagramView) packageView.getTabbedPane().getSelectedComponent();
        if (currentDiagramView==null)
            return;

        JFileChooser jfc = new JFileChooser();
        if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File photoFile = jfc.getSelectedFile();
            BufferedImage image = currentDiagramView.createImage();
            try {
                if (!photoFile.getName().toLowerCase().endsWith(".png")) {
                    photoFile = new File(photoFile.getAbsolutePath() + ".png");
                }
                System.out.println(photoFile.getAbsolutePath());
                ImageIO.write(image, "png", new File(photoFile.getAbsolutePath()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
