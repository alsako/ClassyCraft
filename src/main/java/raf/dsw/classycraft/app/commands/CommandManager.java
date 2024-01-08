package raf.dsw.classycraft.app.commands;

import lombok.Getter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandManager {


    private List<AbstractCommand> komande = new ArrayList<>();

    private int trKomanda = 0;


    public void addCommand(AbstractCommand command){
        while(trKomanda < komande.size()){
                komande.remove(trKomanda);
        }
        komande.add(command);
        doCommand();
    }

    public void doCommand(){
        if(trKomanda < komande.size()){
            komande.get(trKomanda++).doCommand();
        }

        if(trKomanda == komande.size()){
        }
    }

    public void undoCommand() throws NoninvertibleTransformException {
        if(trKomanda > 0){
            komande.get(--trKomanda).undoCommand();
        }

        if(trKomanda == 0){
        }
    }

    public void removeLastCommand(){
        komande.remove(komande.size()-1);
        trKomanda--;
    }

}
