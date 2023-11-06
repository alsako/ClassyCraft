package raf.dsw.classycraft.app.controller;


import raf.dsw.classycraft.app.controller.actionsImpl.AboutUsAction;
import raf.dsw.classycraft.app.controller.actionsImpl.ExitAction;
import raf.dsw.classycraft.app.controller.actionsImpl.NewProjectAction;

public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;

    private NewProjectAction newProjectAction;

    public ActionManager() {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        newProjectAction = new NewProjectAction();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }

    public NewProjectAction getNewProjectAction() {
        return newProjectAction;
    }
}
