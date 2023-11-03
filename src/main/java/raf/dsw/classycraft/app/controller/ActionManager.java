package raf.dsw.classycraft.app.controller;


import raf.dsw.classycraft.app.controller.actionsImpl.AboutUsAction;
import raf.dsw.classycraft.app.controller.actionsImpl.ExitAction;

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
