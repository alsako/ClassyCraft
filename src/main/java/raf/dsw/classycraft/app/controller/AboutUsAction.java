package raf.dsw.classycraft.app.controller;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction{

    public AboutUsAction(){
        putValue(SMALL_ICON, loadIcon("/images/aboutUs.png"));
        putValue(NAME, "AboutUs");
        putValue(SHORT_DESCRIPTION, "About us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
