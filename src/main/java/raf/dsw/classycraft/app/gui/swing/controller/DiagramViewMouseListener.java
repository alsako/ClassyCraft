package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;

import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class DiagramViewMouseListener implements MouseListener, MouseMotionListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        //ne treba
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getPackageView().misKliknut(getCoordinates(e));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getPackageView().misOtpusten(getCoordinates(e));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //ne treba
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //ne treba
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getPackageView().misPrevucen(getCoordinates(e));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //ne treba
    }

    private Point getCoordinates(MouseEvent e){
        AffineTransform transform = MainFrame.getInstance().getPackageView().getCurrentDiagramView().getTransform();
        if (transform!=null){
            try {
                AffineTransform invert = transform.createInverse();
                Point2D.Double p = new Point2D.Double();
                p.setLocation(e.getX(), e.getY());
                Point2D transformedPoint = new Point2D.Double();
                invert.transform(p, transformedPoint);
                return new Point((int) transformedPoint.getX(), (int) transformedPoint.getY());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        Point p = new Point(e.getX(), e.getY());
        return p;
    }
}
