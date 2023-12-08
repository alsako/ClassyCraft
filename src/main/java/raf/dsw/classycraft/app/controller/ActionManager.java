package raf.dsw.classycraft.app.controller;


import lombok.Getter;
import raf.dsw.classycraft.app.controller.actionsImpl.ZoomOutAction;
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
    private ChangeContentAction changeContentAction;
    private DeleteElementAction deleteElementAction;
    private SelectAction selectAction;
    private MoveAction moveAction;
    private DuplicateAction duplicateAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private ZoomToFitAction zoomToFitAction;
    private ResetZoomAction resetZoomAction;

    public ActionManager() {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        newProjectAction = new NewProjectAction();
        deleteNodeAction = new DeleteNodeAction();
        setAuthorAction = new SetAuthorAction();
        newInterclassAction = new NewInterclassAction();
        newConnectionAction = new NewConnectionAction();
        changeContentAction = new ChangeContentAction();
        deleteElementAction = new DeleteElementAction();
        moveAction = new MoveAction();
        selectAction = new SelectAction();
        duplicateAction = new DuplicateAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        zoomToFitAction = new ZoomToFitAction();
        resetZoomAction = new ResetZoomAction();
    }

}
