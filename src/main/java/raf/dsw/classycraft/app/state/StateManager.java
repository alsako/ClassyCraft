package raf.dsw.classycraft.app.state;

import lombok.Getter;
import raf.dsw.classycraft.app.controller.actionsImpl.DuplicateAction;
import raf.dsw.classycraft.app.controller.actionsImpl.MoveAction;

@Getter
public class StateManager {

    private AddConnectionState addConnectionState;
    private AddInterclassState addInterclassState;
    private ChangeContentState changeContentState;
    private DeleteElementState deleteElementState;
    private SelectState selectState;
    private ClassyState currentState;
    private MoveState moveState;
    private DuplicateState duplicateState;

    public StateManager() {
        addConnectionState = new AddConnectionState();
        addInterclassState = new AddInterclassState();
        changeContentState = new ChangeContentState();
        deleteElementState = new DeleteElementState();
        selectState = new SelectState();
        moveState = new MoveState();
        duplicateState = new DuplicateState();
        currentState = selectState;
    }

    public void setAddConnectionState() {
        this.currentState = addConnectionState;
    }
    public void setAddInterclassState() {
        this.currentState = addInterclassState;
    }
    public void setChangeContentState() {this.currentState = changeContentState;}
    public void setDeleteElementState(){this.currentState = deleteElementState;}
    public void setSelectState() {this.currentState = selectState;}
    public void setMoveState(){this.currentState = moveState;}
    public void setDuplicateState(){this.currentState = duplicateState;}
}
