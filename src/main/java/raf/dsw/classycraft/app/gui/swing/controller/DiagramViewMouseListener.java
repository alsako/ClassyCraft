package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
        Point p = new Point();
        p.setLocation(e.getX(), e.getY());
        return p;
    }

}
