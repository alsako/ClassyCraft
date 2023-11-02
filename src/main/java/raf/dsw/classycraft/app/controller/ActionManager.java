package raf.dsw.classycraft.app.controller;


import raf.dsw.classycraft.app.controller.actionImplementations.AboutUsAction;
import raf.dsw.classycraft.app.controller.actionImplementations.ExitAction;

public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;

    public ActionManager() {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }
}
