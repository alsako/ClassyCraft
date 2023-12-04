package raf.dsw.classycraft.app.state;

import lombok.Getter;

@Getter
public class StateManager {

    private AddConnectionState addConnectionState;
    private AddInterclassState addInterclassState;
    private AddMethodState addMethodState;
    private DeleteElementState deleteElementState;
    private ClassyState currentState;

    public StateManager() {
        addConnectionState = new AddConnectionState();
        addInterclassState = new AddInterclassState();
        addMethodState = new AddMethodState();
        deleteElementState = new DeleteElementState();
        currentState = addInterclassState;
    }

    public void setAddConnectionState() {
        this.currentState = addConnectionState;
    }
    public void setAddInterclassState() {
        this.currentState = addInterclassState;
    }
    public void setAddMethodState() {this.currentState = addMethodState;}
    public void setDeleteElementState(){this.currentState = deleteElementState;}
}
