package raf.dsw.classycraft.app.controller;


import lombok.Getter;
import raf.dsw.classycraft.app.controller.actionsImpl.*;

@Getter
public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewProjectAction newProjectAction;
    private DeleteNodeAction deleteNodeAction;
    private SetAuthorAction setAuthorAction;
    private NewInterclassAction newInterclassAction;
    private NewConnectionAction newConnectionAction;
    private NewMethodAction newMethodAction;
    private DeleteElementAction deleteElementAction;

    public ActionManager() {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        newProjectAction = new NewProjectAction();
        deleteNodeAction = new DeleteNodeAction();
        setAuthorAction = new SetAuthorAction();
        newInterclassAction = new NewInterclassAction();
        newConnectionAction = new NewConnectionAction();
        newMethodAction = new NewMethodAction();
        deleteElementAction = new DeleteElementAction();
    }

}
